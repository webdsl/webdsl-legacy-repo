[
   HqlQuery -- _1,
   DslExp -- _1,
   
   Module -- V[H[KW["module"] _1] _2],
   
   Imports -- H[KW["imports"] _1],
   
   Qualified                          -- H hs=0[_1 KW["."] _2],
   Application                        -- V vs=1[H[KW["application"] _1] _2],
   Application.2:iter-star            -- _1,
   Section                            -- V is=2 vs=1 [H[KW["section"] _1 KW["."]] _2],
   Section.1:iter                     -- _1,
   Section.2:iter-star                -- _1,
   Description                        -- V[V is=2[KW["description"] HV[_1]] KW["end"]],
   Note                               -- V[V is=2[KW["note"] HV[_1]] KW["end"]],
   GlobalsDefinition				  -- KW["globals"] KW["{"] _1 KW["}"],
   Entity                             -- V[V is=2[H[_1 KW[":"] _2 KW["{"]] _3 _4] KW["}"]],
   Entity.4:iter-star                 -- _1,
   EntityNoSuper                      -- V[V is=2[H[_1 KW["{"]] _2 _3] KW["}"]],
   EntityNoSuper.3:iter-star          -- _1,
   Task                               -- V[V is=2[H[KW["task"] _1 KW["("] _2 KW[")"] KW["{"]] _3 _4] KW["}"]],
   Task.3:iter-star                   -- _1,
   Task.4:iter-star                   -- _1,
   SessionEntity                      -- KW["session"] _1 KW[":"] _2,
   ExtendEntity						  -- KW["extend"] KW["entity"] _1 KW["{"] _2 _3 KW["}"],
   Property                           -- H[_1 _2 _3 KW["("] _4 KW[")"]],
   Property.4:iter-star-sep           -- H hs=0[_1 KW[","]],
   PropertyNoAnno                     -- H[_1 _2 _3],
   DerivedProperty                    -- H[_1 _2 _3 KW["("] _4 KW[")"] KW[":="] _5],
   DerivedProperty.4:iter-star-sep    -- H hs=0[_1 KW[","]],
   DerivedPropertyNoAnno              -- H[_1 _2 _3 KW[":="] _4],
   Simple                             -- KW["::"],
   Ref                                -- KW["->"],
   Comp                               -- KW["<>"],
   SimpleSort                         -- _1,
   GenericSort                        -- H hs=0[_1 KW["<"] _2 KW[">"]],
   GenericSort.2:iter-sep             -- H hs=0[_1 KW[","]],
   OptionalSort                       -- _1 KW["?"],
   SimpleAnno                         -- _1,
   InverseAnno			      -- KW["inverse"] KW["("] _1 KW["."] _2 KW[")"],
   InverseSlaveAnno		      -- KW["inverseSlave"] KW["("] _1 KW["."] _2 KW[")"],
   ParamAnno                          -- _1 KW["("] _2 KW[")"],
   ParamAnno.2:iter-star-sep          -- _1 KW[","],
   Define                             -- V[ V is=2[H[KW["define"] _1 _2 H hs=0[KW["("] H[_3] KW[")"]] KW["{"]] _4] KW["}"] ],
   Define.1:iter-star                 -- _1,
   Define.3:iter-star-sep             -- H hs=0[_1 KW[","]],
   Define.4:iter-star                 -- _1,
   Page                               -- KW["page"],
   Arg                                -- H[_1 KW[":"] _2],
   Text                               -- H hs=0[ "\"" _1 "\"" ],
   For                                -- V[ V is=2[ H[KW["for"] KW["("] _1 KW[":"] _2 KW["in"] _3 KW[")"] KW["{"] ] _4 ] KW["}"]],
   For.4:iter-star                    -- _1,
   ForAll                             -- V[ V is=2[ H[KW["for"] KW["("] _1 KW[":"] _2 KW[")"] KW["{"] ] _3 ] KW["}"]],
   ForAll.3:iter-star                 -- _1,
   IfTempl                            -- V[ V is=2[ H[KW["if"] KW["("] _1 KW[")"] KW["{"] ] _2 ] KW["}"]],
   IfTempl.2:iter-star                -- _1,
   Select                             -- H[KW["select"] KW["("] _1 KW[":"] _2 KW[","] _3 KW[","] _4 KW[")"]],
   TemplateCallNoArgs                 -- _1,
   True				      -- KW["true"],
   False			      -- KW["false"],
   TemplateCallNoBody                 -- H hs=0[_1 KW["("] H[_2] KW[")"]],
   TemplateCallNoBody.2:iter-star-sep -- H hs=0[_1 KW[","]],
   TemplateCallBody                   -- V[ V is=2[H[_1 KW["{"]] _2] KW["}"]],
   TemplateCallBody.2:iter-star       -- _1,
   TemplateCall                       -- V[ V is=2[ H hs=0[ _1 KW["("] H[_2] KW[")"] KW["{"]] _3] KW["}"]],
   TemplateCall.2:iter-star-sep       -- H hs=0[_1 KW[","]],
   TemplateCall.3:iter-star           -- _1,
   TemplateCallDeclaredType           -- _1 _2 _3 _4,
   Action                             -- V[ H[KW["action"] _1 KW["("] _2 KW[")"]] _3 ],
   Action.2:iter-star-sep             -- H hs=0[_1 KW[","]],
   InitAction                         -- V[ H[KW["init"]] _1],
   Function                           -- V[ H[KW["action"] _1 KW["("] _2 KW[")"] KW[":"] _3] _4 ],
   Function.3:iter-star-sep           -- H hs=0[_1 KW[","]],
   Block                              -- V  [V is=2 [KW["{"] _1] KW["}"]],
   Block.1:iter-star                  -- _1,
   Assign                             -- H hs=0[H[_1] KW[";"]],
   Stat                               -- H hs=0[H[_1] KW[";"]],
   Return                             -- H[KW["return"] H hs=0[H[_1] KW[";"]]],
   VarDeclInit                        -- H[KW["var"] _1 KW[":"] _2 KW[":="] _3 KW[";"]],
   VarDecl                            -- H[KW["var"] _1 KW[":"] _2 KW[";"]],
   If                                 -- V is=2[V[V is=2[H[KW["if"] KW["("] _1 KW[")"]] _2] KW["else"] ] _3],
   IfNoElse                           -- H[KW["if"] KW["("] _1 KW[")"]] _2,
%%   For                                -- KW["for"] KW["("] _1 KW["in"] _2 KW["where"] _3 KW["ordered"] KW["by"] _4 KW["ascending"] KW[")"] _5,
   ForColl                            -- V[H[KW["for"] KW["("] _1 KW[":"] _2 KW["in"] _3 KW["}"]] H is=2[_4] KW["end"]],
   ForExp                            -- H[KW["for"] _1 KW[":"] _2 KW["in"] _3 KW["("] _4 KW[")"]],
   Rules                              -- V  [H  [KW["rules"]] _1],
   Rules.1:iter-star                  -- _1,
   Equation                           -- _1 KW["="] _2,
   Int                                -- _1,
   Float                              -- _1,
   String                             -- H hs=0[ "\"" _1 "\"" ],
   Var                                -- _1,
   FieldAccess                        -- H hs=0[_1 KW["."] _2],
   ObjectCreation                     -- H hs=0[_1 KW["{"] H[_2] KW["}"]],
   ObjectCreation.2:iter-star         -- _1,
   Assignment                         -- H[_1 KW[":="] _2],
   MapCreation                        -- V  [V vs=2 [KW["["] _1] KW["]"]],
   MapCreation.1:iter-star            -- _1,
   Mapping                            -- _1 KW["->"] _2,
   ListCreation                       -- H hs=0[KW["["] H[_1] KW["]"]],
   ListCreation.1:iter-star-sep       -- H hs=0[_1 KW[","]],
   SetCreation                        -- H hs=0[KW["{"] H[_1] KW["}"]],
   SetCreation.1:iter-star-sep        -- H hs=0[_1 KW[","]],
   TypedListCreation                  -- H hs=0[KW["List"] KW["<"] H[_1] KW[">"] KW["("] _2 KW[")"]],
   TypedListCreation.2:iter-star-sep  -- H hs=0[_1 KW[","]],
   TypedSetCreation                   -- H hs=0[KW["Set"] KW["<"] H[_1] KW[">"] KW["("] _2 KW[")"]],
   TypedSetCreation.2:iter-star-sep  -- H hs=0[_1 KW[","]],
   ThisCall                           -- H hs=0[_1 KW["("] H[_2] KW[")"]],
   ThisCall.2:iter-star-sep           -- H hs=0[_1 KW[","]],
   Call                               -- H hs=0[_1 KW["."] _2 KW["("] H[_3] KW[")"]],
   Call.3:iter-star-sep               -- H hs=0[_1 KW[","]],
   InColl                             -- _1 KW["in"] _2,
   Eq                                 -- _1 KW["="] _2,
   NotEq                              -- _1 KW["!="] _2,
   Add                                -- _1 KW["+"] _2,
   Sub                                -- _1 KW["-"] _2,
   Mul                                -- _1 KW["*"] _2,
   Div                                -- _1 KW["/"] _2,
   And                                -- _1 KW["&&"] _2,
   Or                                 -- _1 KW["||"] _2,
   Not                                -- KW["!"] _1,
   IsA                                -- _1 KW["is"] KW["a"] _2,
   Cast                               -- _1 KW["as"] _2,
   
   %%access control syntax
   AccessControlDefinition			  -- KW["access control rules"] KW["{"] _1 KW["}"],
   AccessControlPrincipal			  -- KW["principal is"] _1 KW["with credentials"] _2,
   AccessControlRule 				  -- KW["rules"] _1 _2 KW["{"] _3 KW["}"],
   AccessControlCheckExpression		  -- _1   
]
