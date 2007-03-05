<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:fib="http://namespaces.cafeconleche.org/fibonacci"
  extension-element-prefixes="fib">
  
  <!-- I deleted the validation code from this stylesheet to
       save space, but it would be easy to add back in if
       for production use. -->
  
  <xsl:template match="/methodCall">
    <methodResponse>
      <params>
        <param>
          <value>
            <xsl:apply-templates select="params/param/value" />
          </value>
        </param>
      </params>
    </methodResponse>
  </xsl:template>

  <xsl:template match="value">
    <int>
      <fib:fibonacci>
        <xsl:value-of select="number(.)"/>
        <xsl:fallback>
          <!-- This template will be called only if the 
               fib:fibonacci code can't be loaded. -->
          <xsl:call-template name="calculateFibonacci">
            <xsl:with-param name="index" select="number(.)" />
          </xsl:call-template>
        </xsl:fallback>
      </fib:fibonacci>
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
  
</xsl:stylesheet>
