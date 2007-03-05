<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <!-- Try to make the output look half decent -->
  <xsl:output indent="yes" encoding="ISO-8859-1"/>
  
  <!-- Muenchian method -->
  <xsl:key name="agencies" match="LineItem" use="AgencyCode"/>
  <xsl:key name="bureaus"  match="LineItem" 
    use="concat(AgencyCode,'+',BureauCode)"/>
  <xsl:key name="accounts" match="LineItem" 
    use="concat(AgencyCode,'+',BureauCode,'+',AccountCode)"/>
  <xsl:key name="subfunctions" match="LineItem" 
    use="concat(AgencyCode,'+',BureauCode,'+',AccountCode,
    '+',SubfunctionCode)"/>
  
  <xsl:template match="Budget">
    <Budget year='2001'>
      <xsl:for-each select="LineItem[generate-id() 
       = generate-id(key('agencies',AgencyCode)[1])]">
        <Agency>
          <Name><xsl:value-of select="AgencyName"/></Name>
          <Code><xsl:value-of select="AgencyCode"/></Code>
          <xsl:for-each 
            select="/Budget/LineItem[AgencyCode
            =current()/AgencyCode]
             [generate-id() = 
               generate-id(key('bureaus', 
                     concat(AgencyCode, '+', BureauCode))[1])]">
            <Bureau>
              <Name><xsl:value-of select="BureauName"/></Name>
              <Code><xsl:value-of select="BureauCode"/></Code>
              <xsl:for-each select="/Budget/LineItem
                  [AgencyCode=current()/AgencyCode]
                  [BureauCode=current()/BureauCode]
                  [generate-id() = generate-id(key('accounts',
                   concat(AgencyCode,'+',BureauCode,'+',
                                            AccountCode))[1])]">
                <Account>
                  <Name>
                    <xsl:value-of select="AccountName"/>
                  </Name>
                  <Code>
                    <xsl:value-of select="AccountCode"/>
                  </Code>
                  <xsl:for-each select=
                    "/Budget/LineItem
                     [AgencyCode=current()/AgencyCode]
                     [BureauCode=current()/BureauCode]
                     [AccountCode=current()/AccountCode]
                     [generate-id()=generate-id(
                       key('subfunctions', concat(AgencyCode,'+',
                       BureauCode,'+',AccountCode,'+',
                       SubfunctionCode))[1])]">
                    <Subfunction BEACategory="{BEACategory}"
                     BudgetIndicator="{On-Off-BudgetIndicator}">
                      <Title>
                       <xsl:value-of select="SubfunctionTitle"/>
                      </Title>
                      <Code>
                       <xsl:value-of  select="SubfunctionCode"/>
                      </Code>
                      <Amount>
                        <xsl:value-of select="FY2001"/>
                      </Amount>                       
                    </Subfunction>
                  </xsl:for-each> 
                </Account>
              </xsl:for-each>
            </Bureau>
          </xsl:for-each> 
        </Agency>
      </xsl:for-each>
    </Budget>
  </xsl:template>
  
</xsl:stylesheet>
