[
   Colorer              -- V is=2 [H  [ KW["colorer"] _1 ] V vs=1 [ _2 ] ],
   Colorer.1:iter-star  -- _1,
   Rule                 -- V is=2 [ H hs=0 [ _1 KW[":"] ] _2 _3 _4 ],
   RuleAll              -- V is=2 [ H hs=0 [ KW["all"] _1 KW[":"] ] _2 _3 _4 ],
   CommentLine          -- H hs=1 [ KW["//"] _1 ],
   EmptyLine            -- V [ KW[""]],
   Token                -- _1,
   TK_IDENTIFIER        -- H [ KW["identifier"] KW["token"] ],
   TK_NUMBER            -- H [ KW["number"] KW["token"] ],
   TK_LAYOUT            -- H [ KW["layout"] KW["token"] ],
   TK_STRING            -- H [ KW["string"] KW["token"] ],
   TK_KEYWORD           -- H [ KW["keyword"] KW["token"] ],
   TK_OPERATOR          -- H [ KW["operator"] KW["token"] ],
   TK_VAR               -- H [ KW["var"] KW["token"] ],
   TK_JUNK              -- H [ KW["junk"] KW["token"] ],
   TK_UNKNOWN           -- H [ KW["unknown"] KW["token"] ],
   NORMAL               -- ,
   BOLD                 -- KW["bold"],
   ITALIC               -- KW["italic"],
   ColorDefault         -- KW["_"],
   ColorName            -- _1,
   ColorRGB             -- H [ _1 _2 _3 ],
   RED                  -- KW["red"],
   DARK_RED             -- KW["dark"] KW["red"],
   GREEN                -- KW["green"],
   DARK_GREEN           -- KW["dark"] KW["green"],
   WHITE                -- KW["white"],
   BLACK                -- KW["black"],
   YELLOW               -- KW["yellow"],
   DARK_YELLOW          -- KW["dark"] KW["yellow"],
   BLUE                 -- KW["blue"],
   DARK_BLUE            -- KW["dark"] KW["blue"],
   MAGENTA              -- KW["magenta"],
   DARK_MAGENTA         -- KW["dark"] KW["magenta"],
   GRAY                 -- KW["gray"],
   DARK_GRAY            -- KW["dark"] KW["gray"],
   Outliner             -- V is=2 [H  [KW["outliner"] _1 ] V vs=1 [ _2 ]],
   Outliner.1:iter-star -- _1,
   Folding              -- V is=2 [H  [KW["folding"] _1 ] V vs=1 [ _2 ]],
   Folding.1:iter-star  -- _1,
   Sort                 -- _1,
   ListSort             -- _1 KW["*"],
   Constructor          -- H hs=0 [ KW["_"] KW["."] _1 ],
   ConstructorId        -- _1,
   SortAndConstructor   -- H hs=0 [ _1 KW["."] _2 ],
   Module               -- V is=2 [ H [ KW["module"] _1 ] _2 _3 ],
   Module.3:iter-star   -- _1,
   Imports              -- V is=2 [ KW["imports"] _1 ],
   Imports              -- _1,
   Imports.1:iter-star  -- _1,
   NoImports            -- ,
   Select               -- KW["<id>"]
]
