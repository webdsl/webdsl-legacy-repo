module elib/accesscontrol

  function principal() : Person {
    return securityContext.principal;
  }
 