[
   Colorer              -- V is=2 [H  [KW["colorer"]] _1],
   Colorer.1:iter-star  -- _1,
   Rule                 -- H [           _1 KW[":"] _2 _3 _4 ],
   RuleAll              -- H [ KW["all"] _1 KW[":"] _2 _3 _4 ],
   CommentLine          -- H hs=0 [ KW["//"] _1 ],
   EmptyLine            -- ,
   Token                -- _1,
   TK_IDENTIFIER        -- KW["identifier"] KW["token"],
   TK_NUMBER            -- KW["number"] KW["token"],
   TK_STRING            -- KW["string"] KW["token"],
   TK_KEYWORD           -- KW["keyword"] KW["token"],
   TK_OPERATOR          -- KW["operator"] KW["token"],
   TK_VAR               -- KW["var"] KW["token"],
   TK_JUNK              -- KW["junk"] KW["token"],
   TK_UNKNOWN           -- KW["unknown"] KW["token"],
   NORMAL               -- KW[""],
   BOLD                 -- KW["bold"],
   ITALIC               -- KW["italic"],
   ColorDefault         -- KW["default"],
   ColorName            -- _1,
   ColorRGB             -- _1 _2 _3,
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
   Outliner             -- V is=2 [H  [KW["outliner"]] _1],
   Outliner.1:iter-star -- _1,
   Folding              -- V is=2 [H  [KW["folding"]] _1],
   Folding.1:iter-star  -- _1,
   Sort                 -- _1,
   ListSort             -- _1 KW["*"],
   Constructor          -- H hs=0 [ KW["_"] KW["."] _1 ],
   ConstructorId        -- _1,
   SortAndConstructor   -- H hs=0 [ _1 KW["."] _2 ],
   Module               -- V is=2 [ H [ KW["module"] _1 ] _2 _3 ],
   Module.3:iter-star   -- _1,
   Imports              -- H [ KW["imports"] _1 ],
   Imports.1:iter-sep   -- _1 KW[","],
   Imports              -- _1,
   Imports.1:iter-star  -- _1,
   NoImports            --
]
