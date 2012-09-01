module stdlib/accesscontrol

  function principal() : Person {
    return securityContext.principal;
  }
 