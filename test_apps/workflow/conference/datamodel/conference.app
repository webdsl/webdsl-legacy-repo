module datamodel/conference

section data model conference

  entity Conference {
    name               :: String
    submissionDeadline :: DateTime
    chairs             -> Set<User> 
    pcInvitations      -> Set<PcInvitation>
    pcMembers          -> Set<User>
    papers             -> Set<Paper>
  }
  
/*  status Conference {
    stage :: ConferenceStage
  }
*/

  extend entity User {
/*    roles           -> Set<ConferenceRole>
    tasks           -> Set<ConferenceTask> (inverse=ConferenceTask.assignees)
*/    registered      :: Bool
  }
  
  entity Nix { 
    nix :: Int
  }