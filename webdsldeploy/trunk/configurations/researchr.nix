import ../generate-network.nix {
  applications = [ 
    { name = "researchr";
      src = ../../researchr;
      rootapp = true;
      
    }
  ];
  
  distribution = {
    shelley = { tomcat = true; httpd = true; mysql = true; };
  };

  databasePassword = "admin";
  adminAddr = "foo@bar.com";
}
