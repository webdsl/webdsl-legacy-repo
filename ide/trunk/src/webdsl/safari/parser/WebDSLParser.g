%options package=webdsl.safari.parser
%options template=btParserTemplate.gi
%options import_terminals=WebDSLLexer.gi
%options parent_saved,automatic_ast=toplevel,visitor=preorder,ast_directory=./Ast,ast_type=ASTNode

%Globals
    /.import org.eclipse.imp.parser.IParser;
    import java.util.Hashtable;
    import java.util.Stack;
    ./
%End

%Define
    $ast_class /.Object./
    $additional_interfaces /., IParser./
%End

%Terminals
    --            
    -- Here, you may list terminals needed by this grammar.
    -- Furthermore, a terminal may be mapped into an alias
    -- that can also be used in a grammar rule. In addition,
    -- when an alias is specified here it instructs the
    -- generated parser to use the alias in question when
    -- referring to the symbol to which it is aliased. For
    -- example, consider the following definitions:
    --
    application
    module
    imports
    session
    globals
    function
    inverse
    inverseSlave
    define
    entity
    for
    where
    page
    select
    init
    action
    return
    var
    if
    else
    in
    rules
    task
    List
    Set
    true
    false
    null
         
    IDENTIFIER
    DESCRIPTIONCOMMENT
    NOTECOMMENT
    NUMBER
    STRING
    FLOAT
    PERCENT ::= '%'
    COMMA ::= ','
    DOT ::= '.'
    SEMICOLON ::= ';'
    PLUS ::= '+'
    MINUS ::= '-'
    ASSIGN ::= ':='
    IS ::= '='
    TIMES ::= '*'
    DIVIDE ::= '/'
    GREATER ::= '>'
    LESS ::= '<'
    SMALLERGREATER ::= '<>'
    COLON ::= ':'
    COLONCOLON ::= '::'
    RIGHTARROW ::= '->'
    NOTEQUAL ::= '!='
    EQUAL ::= '=='
    LEFTPAREN ::= '('
    RIGHTPAREN ::= ')'
    LEFTBRACE ::= '{'
    RIGHTBRACE ::= '}'
    LEFTSQUARE ::= '['
    RIGHTSQUARE ::= ']'
    QUESTIONMARK ::= '?'

    --
    -- Here the terminals int, float, IDENTIFIER and NUMBER are
    -- defined without an alias; SEMICOLON is aliased to ';';
    -- PLUS is aliased to '+'... etc...
    --
    -- Note that the terminals that do not have aliases are declared
    -- above only for documentation purposes.
    --
%End

%Start
   Program
%End

%Recover
   Undefined -- I defined a general recovery symbol here for experimentation
%End

%Rules

    Program ::= Module | Application
   
    Module ::= module ModuleName SectionList
    
    Application ::= application QId SectionList
    /.
        $action_type.SymbolTable symbolTable;
        public void setSymbolTable($action_type.SymbolTable symbolTable) { this.symbolTable = symbolTable; }
        public $action_type.SymbolTable getSymbolTable() { return symbolTable; }
    ./    

    Id         ::= IDENTIFIER
    QId$$Id    ::= Id
                 | QId '.' Id
    ModuleName$$Id ::= Id
                     | ModuleName '/' Id

    SectionList$$Section ::= %Empty
                           | SectionList Section
   
    Section ::= SECTIONCOMMENT DefinitionList
              | imports ModuleName
              | Module
              | DESCRIPTIONCOMMENT
              | NOTECOMMENT
    
    Definition ::= Entity | Globals | TemplateDefinition | Action | Rules
    DefinitionList$$Definition ::= %Empty
                                 | DefinitionList Definition
    
    Globals ::= globals '{' VarDeclList '}'
    
    Entity ::= entity PutId ':' Id '{' PropertyList FunctionList '}'
             | entity PutId '{' PropertyList FunctionList '}'
             | session PutId ':' Entity
             | task PutId '(' STRING ')' '{' PropertyList FunctionList '}'

    Function ::= 'function' PutId '(' FormalArgList ')' ':' Sort Block
    FunctionList$$Function ::= %Empty
                             | FunctionList Function

    Property ::= PutId PropKind Sort '(' AnnotationList ')'
               | PutId PropKind Sort
               | PutId PropKind Sort '(' AnnotationList ')' ':=' Exp
               | PutId PropKind Sort ':=' Exp
    PropertyList$$Property ::= %Empty
                             | PropertyList Property
             
    PropKind ::= '::' | '->' | '<>'
    
    Sort ::= Id
           | Id '<' SortList '>'
           | Id '?'
    SortList$$Sort ::= Sort
                     | SortList ',' Sort
    
    Annotation ::= Id
                 | inverse '=' Id '.' Id
                 | inverseSlave '=' Id '.' Id
    AnnotationPlus$$Annotation ::= Annotation
                                 | AnnotationPlus ',' Annotation
    AnnotationList ::= AnnotationPlus | %Empty
    
    FormalArg ::= PutId ':' Sort
    FormalArgPlus$$FormalArg ::= FormalArg
                               | FormalArgPlus ',' FormalArg
    FormalArgList ::= FormalArgPlus | %Empty
    
    TemplateDefinition ::=
      define ModifierList PutId '(' FormalArgList ')' 
                                '{' TemplateElementList '}'
     
    Modifier ::= page
    ModifierList$$Modifier ::= %Empty
                             | ModifierList Modifier
    
    TemplateElement ::= InitAction | Action | VarDecl
                      | TemplateDefinition | TemplateCall | STRING
                      | ForTemplate | Select
    TemplateElementList$$TemplateElement ::= %Empty
                                          | TemplateElementList TemplateElement
    
    ForTemplate ::= for '(' PutId ':' Sort in Exp ')' '{' TemplateElementList '}'
                  | for '(' PutId ':' Sort        ')' '{' TemplateElementList '}'
    
    Select ::= select '(' Id ':' Sort ',' STRING ',' Exp ')'
    
    TemplateCall ::= GetId
                   | GetId '(' ExpList ')'
                   | GetId                 '{' TemplateElementList '}'
                   | GetId '(' ExpList ')' '{' TemplateElementList '}'
    
    InitAction ::= init Block
    
    Action ::= action PutId '(' FormalArgList ')' Block

    Statement ::= Block | Assignment ';' | Exp ';' | return Exp ';' | VarDecl
                | If | For
    StatementList$$Statement ::= %Empty
                               | StatementList Statement
    
    Block ::= '{' StatementList '}'
    
    VarDecl ::= var PutId ':' Sort ';'
              | var PutId ':' Sort ':=' Exp ';'
    VarDeclList$$VarDecl ::= %Empty
                           | VarDeclList VarDecl
              
    If ::= if Exp '{' StatementList else StatementList '}'
         | if Exp '{' StatementList                    '}'
    
    For ::= for '(' PutId ':' Sort in Exp ')' '{' StatementList '}'
          | for '(' PutId ':' Sort in Exp ')' '{' StatementList '}' where Exp
    
    Rule ::= Exp '=' Exp
    RuleList$$Rule ::= %Empty
                     | RuleList Rule
    Rules ::= rules RuleList

    Exp ::= Int | FloatLiteral | STRING
          | GetId
          | Exp '.' Id
          | Id '{' AssignmentList '}'
          | QId ':=' Exp
          | '[' MappingList ']'
          | '[' ExpList ']'
          | '{' ExpList '}'
          | List '<' Sort '>' '(' ')'
          | Set  '<' Sort '>' '(' ')'
          | Id '(' ExpList ')'
          | Exp '.' Id '(' ExpList ')'
          | Exp '==' Exp
          | Exp '!=' Exp
          | true
          | false
          | null
          | Exp '[' for PutId ':' Sort in Exp ']'
          | Exp '[' for PutId ':' Sort in Exp where Exp ']'
          | Exp in Exp
          | Exp '*' Exp
          | Exp '/' Exp
          | Exp '%' Exp
          | Exp '+' Exp
          | Exp '-' Exp
    
    PutId ::= IDENTIFIER -- declares an identifier

    GetId ::= IDENTIFIER -- fetches an identifier
    /.
        IAst decl;
        public void setDeclaration(IAst decl) { this.decl = decl; }
        public IAst getDeclaration() { return decl; }
    ./
          
    ExpList$$Exp ::= %Empty
                    | ExpList Exp
   
    -- TODO: Handle HQL

    Int ::= NUMBER
    FloatLiteral ::= FLOAT

    Assignment ::= QId ':=' Exp
    AssignmentList$$Assignment ::= %Empty
                                 | AssignmentList Assignment
    
    Mapping ::= Exp '->' Exp
    MappingList$$Mapping ::= %Empty
                          | MappingList Mapping
%End

%Headers
    /.
        public class SymbolTable extends Hashtable {
            SymbolTable parent;
            SymbolTable(SymbolTable parent) { this.parent = parent; }
            public IAst findDeclaration(String name) {
                IAst decl = (IAst) get(name);
                return (decl != null
                              ? decl
                              : parent != null ? parent.findDeclaration(name) : null);
            }
            public SymbolTable getParent() { return parent; }
        }
        
        Stack symbolTableStack = null;
        SymbolTable topLevelSymbolTable = null;
        public SymbolTable getTopLevelSymbolTable() { return topLevelSymbolTable; }

        public SymbolTable getEnclosingSymbolTable(IAst n) {
            for ( ; n != null; n = n.getParent())
                if (n instanceof Application)
                     return ((Application) n).getSymbolTable();
            return getTopLevelSymbolTable();
        }

        public void resolve($ast_type root) {
            if (root != null) {
                symbolTableStack = new Stack();
                topLevelSymbolTable = new SymbolTable(null);
                symbolTableStack.push(topLevelSymbolTable);
                root.accept(new SymbolTableVisitor());
            }
        }
        
        /*
         * A visitor for ASTs.  Its purpose is to build a symbol table
         * for declared symbols and resolved identifier in expressions.
         */
        private final class SymbolTableVisitor extends AbstractVisitor {
            public void unimplementedVisitor(String s) { /* Useful for debugging: System.out.println(s); */ }
            
            public void emitError(IToken id, String message) {
                getMessageHandler().handleMessage(ParseErrorCodes.NO_MESSAGE_CODE,
                                                  getLexStream().getLocation(id.getStartOffset(), id.getEndOffset()),
                                                  getLexStream().getLocation(0, 0),
                                                  getFileName(),
                                                  new String [] { message });
            }
            
            
            public void emitError(ASTNode node, String message) {
                getMessageHandler().handleMessage(
                    ParseErrorCodes.NO_MESSAGE_CODE,
                    getLexStream().getLocation(
                        node.getLeftIToken().getStartOffset(), node.getRightIToken().getEndOffset()),
                    getLexStream().getLocation(0, 0),
                    getFileName(),
                    new String [] { message });
            }

           public void emitError(int startOffset, int endOffset, String message) {
                getMessageHandler().handleMessage(
                    ParseErrorCodes.NO_MESSAGE_CODE,
                    getLexStream().getLocation(startOffset, endOffset),
                    getLexStream().getLocation(0, 0),
                    getFileName(),
                    new String [] { message });
            }

            /* UNDONE: Using GetId/PutId instead
            public boolean visit(TemplateDefinition n) {
                IToken id = n.getId().getIToken();
                SymbolTable symbol_table = (SymbolTable) symbolTableStack.peek();
                if (symbol_table.get(id.toString()) == null)
             	     symbol_table.put(id.toString(), n);
                else emitError(id, "Illegal redeclaration of " + id.toString());
        
                //
                // TODO: Add a symbol table for the parameters
                //
                // n.setSymbolTable((SymbolTable) symbolTableStack.push(new SymbolTable((SymbolTable) symbolTableStack.peek())));
    
                return true;
            }
            
            public void endVisit(TemplateDefinition n) { symbolTableStack.pop(); }

            public boolean visit(VarDecl0 n) {
                IToken id = n.getidentifier().getIToken();
                SymbolTable symbol_table = (SymbolTable) symbolTableStack.peek();
                if (symbol_table.get(id.toString()) == null)
                     symbol_table.put(id.toString(), n);
                else emitError(id, "Illegal redeclaration of " + id.toString());
                return true;
            }
            */

            public boolean visit(PutId n) {
                IToken id = n.getIDENTIFIER();
                SymbolTable symbol_table = (SymbolTable) symbolTableStack.peek();
                if (symbol_table.get(id.toString()) == null)
                     symbol_table.put(id.toString(), n);
                else emitError(id, "Illegal redeclaration of " + id.toString());
                return true;
            }

            public boolean visit(GetId n) {
                IToken id = n.getIDENTIFIER();
                IAst decl = ((SymbolTable) symbolTableStack.peek()).findDeclaration(id.toString());
                if (decl == null)
                     emitError(id, "Undeclared variable " + id.toString());
                else n.setDeclaration(decl);
                return true;
            }
        } // End SymbolTableVisitor
    ./
%End
-- Need a carriage return here
