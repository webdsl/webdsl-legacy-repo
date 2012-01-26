webdsldeploy
============
webdsldeploy is a utility which can be used in conjuncion with various
Nix-related tools, to manage the deployment process of WebDSL applications from
a declarative specification.

The webdsldeploy function generates a NixOS network specification that builds a
WebDSL application and configures all the underlying infrastructure
components, such as Apache Tomcat and MySQL.

The resulting NixOS network specification can be used to test a configuration in
virtual machines or to deploy a network of physical or cloud machines.

Prerequisites
=============
NixOS, http://nixos.org/nixos with Charon (https://github.com/NixOS/charon)

or a regular Linux distribution with the following:

- Nix, http://nixos.org/nix
- NixOS source code, http://nixos.org/repos/nix/nixos/trunk
- Nixpkgs source, http://nixos.org/repos/nix/nixos/nixpkgs/trunk
- Charon, https://github.com/NixOS/charon
- KVM (for using virtual machines), http://linux-kvm.org

Writing deployment expressions
==============================

A very simple deployment expression (app.nix) describing the deployment of a
WebDSL application on a single machine, looks like this:

import ./generate-network.nix {
  applications = [ 
    { name = "myapp";
      src = /path/to/myapp/source;
      rootapp = true;
    }
  ];

  databasePassword = "admin";
  adminAddr = "foo@bar.com";
}

By providing an extra distribution parameter, you can create a network of machines
in which infrastructure components can be distributed across the network:

import ./generate-network.nix {
  applications = [ 
    { name = "myapp";
      src = /path/to/myapp/source;
      rootapp = true;
    }
  ];

  distribution = {
    test1 = {
      tomcat = true;
      httpd = true;
    };
      
    test2 = {
      mysql = true;
    };
  }
}

The expression above defines a network of 2 machines. Machine test1 hosts the
Apache Tomcat server and the Apache HTTP proxy. Machine test2 hosts the MySQL
DBMS server to store data entities.

You can also define multiple instances of MySQL and Apache Tomcat services:

import ./generate-network.nix {
  applications = [ 
    { name = "myapp";
      src = /path/to/myapp/source;
      rootapp = true;
    }
  ];

  distribution = {
    test0 = {
      proxy = true;
    };
    
    test1 = {
      tomcat = true;
      httpd = true;
      mysqlMaster = true;
    };
      
    test2 = {
      tomcat = true;
      httpd = true;
      mysqlSlave = 2;
    };
  }

  databasePassword = "admin";
  adminAddr = "foo@bar.com";
}

In this example, the deployment function automatically creates a master-slave database
configuration. The reverse proxy on test0 automatically forwards incoming
connections to the Apache Tomcat instances using the round-robin schulding method 

Usage
=====

A deployment expression can be used in conjunction with nixos-build-vms to
test the configuration in a network of virtual machines:

$ nixos-build-vms app.nix
$ ./result/bin/nixos-run-vms

By using charon and an expression to capture physical network characteristics,
you can deploy the configuration in a network of physical machines or cloud
machines:

$ charon -s state --create app.nix ec2.nix
$ charon -s state --deploy
