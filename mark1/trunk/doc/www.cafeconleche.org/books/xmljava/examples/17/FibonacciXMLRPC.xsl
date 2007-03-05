<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <xsl:template match="/">
    <xsl:choose>
      <!-- Basic sanity check on the input -->
      <xsl:when 
        test="count(methodCall/params/param/value/int) = 1">
        <xsl:apply-templates select="child::methodCall"/>
      </xsl:when>
      <xsl:otherwise>
        <!-- Sanity check failed -->
        <xsl:call-template name="faultResponse"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
  <xsl:template match="methodCall">
    <methodResponse>
      <params>
        <param>
          <value>
            <xsl:apply-templates 
              select="params/param/value/int"/>
          </value>
        </param>
      </params>
    </methodResponse>
  </xsl:template>

  <xsl:template match="int">
    <int>
      <xsl:call-template name="calculateFibonacci">
        <xsl:with-param name="index" select="number(.)"/>
      </xsl:call-template>
    </int>
  </xsl:template>

  <xsl:template name="calculateFibonacci">
    <xsl:param name="index"/>
    <xsl:param name="low"  select="1"/>
    <xsl:param name="high" select="1"/>
    <xsl:choose>
      <xsl:when test="$index &lt;= 1">
        <xsl:value-of select="$low"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:call-template name="calculateFibonacci">
          <xsl:with-param name="index" select="$index - 1"/>
          <xsl:with-param name="low"   select="$high"/>
          <xsl:with-param name="high"  select="$high + $low"/>
        </xsl:call-template>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="faultResponse">
    <xsl:param name="err_code"    select="0" />    
    <xsl:param name="err_message" select="'Unspecified Error'"/>    
    <methodResponse>
      <fault>
        <value>
          <struct>
            <member>
              <name>faultCode</name>
              <value>
                <int><xsl:value-of select="$err_code"/></int>
              </value>
            </member>
            <member>
              <name>faultString</name>
              <value>
                <string>
                  <xsl:value-of select="$err_message"/>
                </string>
              </value>
            </member>
          </struct>
        </value>
      </fault>
    </methodResponse>
  </xsl:template>  
  
</xsl:stylesheet>
