import ../generate-network.nix {
  applications = [ 
    { name = "researchr";
      src = ../../researchr;
      rootapp = true;
    }
  ];
    
  databasePassword = "admin";
  adminAddr = "foo@bar.com";
}
