module global-settings

  var globalSettings := Settings{}; 
  
  entity Settings {
    labelWidth :: Int := 125
    formButtonsStyle :: String := "width:100%; clear:left; float:left; margin-top: 5px; text-align: right;"
  }