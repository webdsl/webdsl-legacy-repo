<?xml version="1.0" ?>
<HTML xmlns:xsl="http://www.w3.org/TR/WD-xsl">
  <HEAD>
    <TITLE>
      <xsl:for-each select="SEASON">
        <xsl:value-of select="@YEAR"/> 
      </xsl:for-each> 
      Major League Baseball Statistics
    </TITLE>
  </HEAD>
  <BODY>
  
    <xsl:for-each select="SEASON">
      <H1 ALIGN="CENTER">
        <xsl:value-of select="@YEAR"/> 
        Major League Baseball Statistics
      </H1>
      
      <xsl:for-each select="LEAGUE">
        <H2 ALIGN="CENTER">
        <xsl:value-of select="@NAME"/> 
        </H2>
        
          <xsl:for-each select="DIVISION">
            <H3 ALIGN="CENTER">
            <xsl:value-of select="@NAME"/> 
            </H3>
            
            <xsl:for-each select="TEAM">
              <H4 ALIGN="CENTER">
              <xsl:value-of select="@CITY"/> 
              <xsl:value-of select="@NAME"/> 
              </H4> 
              
              <TABLE>
               <CAPTION><B>Batters</B></CAPTION>
               <THEAD>
                <TR>
                 <TH>Player</TH><TH>P</TH><TH>G</TH>
                 <TH>GS</TH><TH>AB</TH><TH>R</TH><TH>H</TH>
                 <TH>D</TH><TH>T</TH><TH>HR</TH><TH>RBI</TH>
                 <TH>S</TH><TH>CS</TH><TH>SH</TH><TH>SF</TH>
                 <TH>E</TH><TH>BB</TH><TH>SO</TH><TH>HBP</TH>
                </TR>
               </THEAD>
              <TBODY>
               <xsl:for-each select="PLAYER[(@POSITION != 'Starting Pitcher') 
                $and$ (@POSITION != 'Relief Pitcher')]">
                <TR>
                 <TD>
                  <xsl:value-of select="@GIVEN_NAME"/> 
                  <xsl:value-of select="@SURNAME"/>
                 </TD>
                 <TD><xsl:value-of select="@POSITION"/></TD>
                 <TD><xsl:value-of select="@GAMES"/></TD>
                 <TD>
                   <xsl:value-of select="@GAMES_STARTED"/>
                 </TD>
                 <TD><xsl:value-of select="@AT_BATS"/></TD>
                 <TD><xsl:value-of select="@RUNS"/></TD>
                 <TD><xsl:value-of select="@HITS"/></TD>
                 <TD><xsl:value-of select="@DOUBLES"/></TD>
                 <TD><xsl:value-of select="@TRIPLES"/></TD>
                 <TD><xsl:value-of select="@HOME_RUNS"/></TD>
                 <TD><xsl:value-of select="@RBI"/></TD>
                 <TD><xsl:value-of select="@STEALS"/></TD>
                 <TD>
                  <xsl:value-of select="@CAUGHT_STEALING"/>
                 </TD>
                 <TD>
                  <xsl:value-of select="@SACRIFICE_HITS"/>
                 </TD>
                 <TD>
                  <xsl:value-of select="@SACRIFICE_FLIES"/>
                 </TD>
                 <TD><xsl:value-of select="@ERRORS"/></TD>
                 <TD><xsl:value-of select="@WALKS"/></TD>
                 <TD>
                  <xsl:value-of select="@STRUCK_OUT"/>
                 </TD>
                 <TD>
                  <xsl:value-of select="@HIT_BY_PITCH"/>
                 </TD>
                </TR> 
               </xsl:for-each>  <!-- PLAYER -->
              </TBODY>
             </TABLE>

              <TABLE>
               <CAPTION><B>Pitchers</B></CAPTION>
               <THEAD>
                <TR>
                 <TH>Player</TH><TH>P</TH><TH>G</TH>
                 <TH>GS</TH><TH>W</TH><TH>L</TH><TH>S</TH>
                 <TH>CG</TH><TH>SO</TH><TH>ERA</TH><TH>IP</TH>
                 <TH>HR</TH><TH>R</TH><TH>ER</TH><TH>HB</TH>
                 <TH>WP</TH><TH>B</TH><TH>BB</TH><TH>K</TH>
                </TR>
               </THEAD>
              <TBODY>
               <xsl:for-each select="PLAYER[(@POSITION = 'Starting Pitcher') 
                $or$ (@POSITION = 'Relief Pitcher')]">
                <TR>
                 <TD>
                  <xsl:value-of select="@GIVEN_NAME"/> 
                  <xsl:value-of select="@SURNAME"/>
                 </TD>
                 <TD><xsl:value-of select="@POSITION"/></TD>
                 <TD><xsl:value-of select="@GAMES"/></TD>
                 <TD>
                   <xsl:value-of select="@GAMES_STARTED"/>
                 </TD>
                 <TD><xsl:value-of select="@WINS"/></TD>
                 <TD><xsl:value-of select="@LOSSES"/></TD>
                 <TD><xsl:value-of select="@SAVES"/></TD>
                 <TD><xsl:value-of select="@COMPLETE_GAMES"/></TD>
                 <TD><xsl:value-of select="@SHUT_OUTS"/></TD>
                 <TD><xsl:value-of select="@ERA"/></TD>
                 <TD><xsl:value-of select="@INNINGS"/></TD>
                 <TD><xsl:value-of select="@HOME_RUNS_AGAINST"/></TD>
                 <TD>
                  <xsl:value-of select="@RUNS_AGAINST"/>
                 </TD>
                 <TD>
                  <xsl:value-of select="@EARNED_RUNS"/>
                 </TD>
                 <TD>
                  <xsl:value-of select="@HIT_BATTER"/>
                 </TD>
                 <TD><xsl:value-of select="@WILD_PITCH"/></TD>
                 <TD><xsl:value-of select="@BALK"/></TD>
                 <TD>
                  <xsl:value-of select="@WALKED_BATTER"/>
                 </TD>
                 <TD>
                  <xsl:value-of select="@STRUCK_OUT_BATTER"/>
                 </TD>
                </TR> 
               </xsl:for-each>  <!-- PLAYER -->
              </TBODY>
             </TABLE>
              
            </xsl:for-each> <!-- TEAM -->                       
          </xsl:for-each> <!-- DIVISION -->                   
      </xsl:for-each> <!-- LEAGUE -->           
    </xsl:for-each> <!-- SEASON -->

    <HR></HR>
    Copyright 1999 
    <A HREF="http://www.macfaq.com/personal.html">
     Elliotte Rusty Harold
    </A>
    <BR />
    <A HREF="mailto:elharo@metalab.unc.edu">
     elharo@metalab.unc.edu
    </A>

  </BODY>
</HTML>