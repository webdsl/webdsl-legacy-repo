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
   Description                        -- V[V is=2[KW["description"] KW["{"] HV[_1]] KW["}"]],
   Note                               -- V[V is=2[KW["note"] HV[_1]] KW["end"]],
   GlobalsDefinition                  -- KW["globals"] KW["{"] _1 KW["}"],
   Entity                             -- V[V is=2[H[KW["entity"] _1 KW[":"] _2 KW["{"]] _3 _4] KW["}"]],
   Entity.4:iter-star                 -- _1,
   EntityNoSuper                      -- V[V is=2[H[KW["entity"] _1 KW["{"]] _2 _3] KW["}"]],
   EntityNoSuper.3:iter-star          -- _1,
   Task                               -- V[V is=2[H[KW["task"] _1 KW["("] _2 KW[")"] KW["{"]] _3 _4] KW["}"]],
   Task.3:iter-star                   -- _1,
   Task.4:iter-star                   -- _1,
   SessionEntity                      -- V[V is=2[H[KW["session"] _1 KW["{"]] _2 _3] KW["}"]],
   SessionEntity.2:iter-star          -- _1,
   SessionEntity.3:iter-star          -- _1,
   ExtendEntity                       -- KW["extend"] KW["entity"] _1 KW["{"] _2 _3 KW["}"],
   ExtendSessionEntity                -- KW["extend"] KW["session"] _1 KW["{"] _2 _3 KW["}"],
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
   Email                              -- KW["email"],
   Template                           -- KW["template"],
   Local                              -- KW["local"],
   Arg                                -- H[_1 KW[":"] _2],
   Text                               -- H hs=0[ "\"" _1 "\"" ],

   For                                -- V[ V is=2[ H[KW["for"] KW["("] _1 KW[":"] _2 KW["in"] _3 _4 KW[")"] KW["{"] ] _5 ] KW["}"]],
   For.5:iter-star                    -- _1,

   ForAll                             -- V[ V is=2[ H[KW["for"] KW["("] _1 KW[":"] _2 _3 KW[")"] KW["{"] ] _4 ] KW["}"]],
   ForAll.4:iter-star                 -- _1,


   Subtable                           -- V[ V is=2[ H[KW["subtable"] KW["("] _1 KW[":"] _2 KW["in"] _3 KW[")"] KW["{"] ] _4 ] KW["}"]],
   IfTempl                            -- V[ V is=2[ H[KW["if"] KW["("] _1 KW[")"] KW["{"] ] _2 ] KW["}"]],
   IfTempl.2:iter-star                -- _1,
   Select                             -- H[KW["select"] KW["("] _1 KW[":"] _2 KW[","] _3 KW[","] _4 KW[")"]],
   SelectFromList                     -- H[KW["select"] KW["("] _1 KW["from"] _2 KW[")"]],
   TemplateCallNoArgs                 -- _1,
   True				                        -- KW["true"],
   False			                        -- KW["false"],
   Null                               -- KW["null"],
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
   Function                           -- V[ H[KW["function"] _1 KW["("] _2 KW[")"] KW[":"] _3] _4 ],
   Function.3:iter-star-sep           -- H hs=0[_1 KW[","]],
   Block                              -- V  [V is=2 [KW["{"] _1] KW["}"]],
   Block.1:iter-star                  -- _1,
   Assign                             -- H hs=0[H[_1] KW[";"]],
   Stat                               -- H hs=0[H[_1] KW[";"]],
   Return                             -- H[KW["return"] H hs=0[H[_1] KW[";"]]],
   Cancel                             -- H[KW["cancel"] H hs=0[H[_1] KW[";"]]],
   GoTo								  -- H[KW["goto"] H hs=0[H[_1] KW[";"]]],
   VarDeclInit                        -- H[KW["var"] _1 KW[":"] _2 KW[":="] _3 KW[";"]],
   VarDecl                            -- H[KW["var"] _1 KW[":"] _2 KW[";"]],
   If                                 -- V is=2[V[V is=2[H[KW["if"] KW["("] _1 KW[")"]] _2] KW["else"] ] _3],
   IfNoElse                           -- H[KW["if"] KW["("] _1 KW[")"]] _2,

   Filter                             -- KW["where"] _1 KW["order"] KW["by"] _2,
   OrderNonSpecific                   -- _1,
   OrderAscending                     -- _1 KW["asc"],
   OrderDescending                    -- _1 KW["desc"],

   ForStmt                            -- V[H[KW["for"] KW["("] _1 KW[":"] _2 KW["in"] _3 _4 KW[")"] ] _5],
   ForStmt.5:iter-star                -- _1,

   ForExp                             -- H[KW["["] _1 KW["|"] _2 KW[":"] _3 KW["in"] _4 _5 KW["]"]],
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
   TypedSetCreation.2:iter-star-sep   -- H hs=0[_1 KW[","]],
   ThisCall                           -- H hs=0[_1 KW["("] H[_2] KW[")"]],
   ThisCall.2:iter-star-sep           -- H hs=0[_1 KW[","]],
   Call                               -- H hs=0[_1 KW["."] _2 KW["("] H[_3] KW[")"]],
   Call.3:iter-star-sep               -- H hs=0[_1 KW[","]],
   InColl                             -- _1 KW["in"] _2,
   Eq                                 -- _1 KW["="] _2,
   NotEq                              -- _1 KW["!="] _2,
   LargerThan                         -- _1 KW[">"] _2,
   LargerThanOrEqual                  -- _1 KW[">="] _2,
   SmallerThan                        -- _1 KW["<"] _2,
   SmallerThanOrEqual                 -- _1 KW["<="] _2,
   Add                                -- _1 KW["+"] _2,
   Sub                                -- _1 KW["-"] _2,
   Mul                                -- _1 KW["*"] _2,
   Mod                                -- _1 KW["%"] _2,
   Div                                -- _1 KW["/"] _2,
   And                                -- _1 KW["&&"] _2,
   Or                                 -- _1 KW["||"] _2,
   Not                                -- KW["!"] _1,
   IsA                                -- _1 KW["is"] KW["a"] _2,
   Cast                               -- _1 KW["as"] _2,
   None                               -- KW["None()"], %% Shoudl never apear


   %%access control syntax
   AccessControlDefinition                      -- KW["access"] KW["control"] KW["rules"] _1 KW["{"] _2 KW["}"],
   AccessControlDefinition.1:opt                -- _1,
   AccessControlDefinition.2:iter-star          -- _1,
   AccessControlPrincipal                       -- KW["principal"] KW["is"] _1 KW["with"] KW["credentials"] _2,
   AccessControlPrincipal.2:iter-sep            -- _1 KW[","],
   AccessControlRule                            -- KW["rules"] _1 _2 KW["("] _3 KW[")"] KW["{"] _4 _5 KW["}"],
   AccessControlRule.5:iter-star                -- _1,
   MatchArgs                                    -- _1 _2,
   MatchArgs.1:iter-star-sep                    -- _1 KW[","],
   MatchArgs.2:opt                              -- _1,
   MatchArgs                                    -- _1 _2,
   MatchArgs.1:iter-star-sep                    -- _1 KW[","],
   MatchArgs.2:opt                              -- _1,
   AccessControlCheckExpression                 -- _1,
   Predicate                                    -- KW["predicate"] _1 KW["("] _2 KW[")"] KW["{"] _3 KW["}"],
   Predicate.2:iter-star-sep                    -- _1 KW[","],
   AccessControlPointcutElement                 -- _1 _2 KW["("] _3 _4 KW[")"],
   AccessControlPointcutElement.3:iter-star-sep -- _1 KW[","],
   AccessControlPointcutElement.4:opt           -- _1,
   AccessControlPointcut                        -- KW["pointcut"] _1 KW["("] _2 KW[")"] KW["{"] _3 KW["}"],
   AccessControlPointcut.2:iter-star-sep        -- _1 KW[","],
   AccessControlPointcut.3:iter-star-sep        -- _1 KW[","],
   ACPolicy                                     -- KW["access"] KW["control"] KW["policy"] _1,


   %%validation annos
   ValidationAnnoLength                         -- KW["length"] KW["("] _1 KW[","] _2 KW[")"],
   ValidationAnnoMinLength                      -- KW["minlength"] KW["("] _1 KW[")"],
   ValidationAnnoMaxLength                      -- KW["maxlength"] KW["("] _1 KW[")"],
   ValidationAnnoNotEmpty                       -- KW["notempty"],
   ValidationAnnoEmail                          -- KW["email"],
   ValidationAnnoCreditCardNumber               -- KW["creditcardnumber"],
   ValidationAnnoEAN                            -- KW["EAN"],
   ValidationAnnoPattern                        -- KW["pattern"] KW["("] _1 KW[")"],
   ValidationAnnoPatternWithFlags               -- KW["pattern"] KW["("] _1 KW[","] _2 KW[")"],
   ValidationAnnoPatternWithFlags.2:iter-star   -- _1,

   FlagCanonEq                                  -- KW["canonicalequivalence"],
   FlagCaseInsensitive                          -- KW["caseinsensitive"],
   FlagComments                                 -- KW["comments"],
   FlagDotAll                                   -- KW["dotall"],
   FlagLiteral                                  -- KW["literal"],
   FlagMultiline                                -- KW["multiline"],
   FlagUnicodeCase                              -- KW["unicodecase"],
   FlagUnixLines                                -- KW["unixlines"],

   ValidationAnnoMax                            -- KW["max"] KW["("] _1 KW[")"],
   ValidationAnnoMin                            -- KW["min"] KW["("] _1 KW[")"],
   ValidationAnnoRange                          -- KW["range"] KW["("] _1 KW[","] _2 KW[")"],
   ValidationAnnoMinRange                       -- KW["minrange"] KW["("] _1 KW[")"],
   ValidationAnnoMaxRange                       -- KW["maxrange"] KW["("] _1 KW[")"],
   ValidationAnnoDigits                         -- KW["digits"] KW["("] _1 KW[","] _2 KW[")"],
   ValidationAnnoPast                           -- KW["past"],
   ValidationAnnoFuture                         -- KW["future"],
   ValidationAnnoSize                           -- KW["size"] KW["("] _1 KW[","] _2 KW[")"],
   ValidationAnnoMinSize                        -- KW["minsize"] KW["("] _1 KW[")"],
   ValidationAnnoMaxSize                        -- KW["maxsize"] KW["("] _1 KW[")"],
   ValidationAnnoNotNull                        -- KW["notnull"],
   ValidationAnnoAssertTrue                     -- KW["asserttrue"],
   ValidationAnnoAssertFalse                    -- KW["assertfalse"],
   ValidationAnnoValid                          -- KW["valid"],

   %%styling
   ThemeDefinition                              -- KW["theme"] _1 _2 KW["("] _3 KW[")"] KW["{"] _4 KW["}"],
   ThemeDefinition.3:iter-star                  -- _1,
   ThemeDefinition.4:iter-star                  -- _1,
   LayoutDefinition                             -- KW["layout"] _1 KW["{"] _2 KW["}"],
   StyleSection                                 -- KW["style"] _1 _2,
   StyleSection.2:iter-star                     -- _1,
   StyleDeclaration                             -- _1 KW[":="] _2,
   VertLayoutDeclarations                       -- _1,
   VertLayoutDeclarations.1:iter-star           -- _1,
   VertLayoutDeclaration                        -- _1 KW[";"],
   HorLayoutDeclaration                         -- _1 KW["("] _2 KW[")"],
   HorLayoutDeclaration                         -- _1,
   HorLayoutDeclaration.1:iter-sep              -- _1 KW["|"],
   TemplateSignature                            -- _1 _2,
   TemplateSignature.2:opt                      -- _1,
   StyleDescription                             -- _1 KW["{"] _2 KW["}"],
   StyleDescription.2:iter-star                 -- _1,
   MatchTemplateArgs                            -- KW["("] _1 KW[")"],
   MatchTemplateArgs.1:iter-star-sep            -- _1 KW[","],
   StyleProperty                                -- _1,
   StyleValue                                   -- _1,
   StyleValue.1:alt                             -- _1 _1 _1 _1 _1 _1 _1 _1 _1 _1 _1 _1 _1
 
]
