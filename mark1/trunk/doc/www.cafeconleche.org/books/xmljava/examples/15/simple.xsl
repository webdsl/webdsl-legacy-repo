<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:svg="http://www.w3.org/2000/svg">
  
  <xsl:template match="svg:rect">
    <rectangle><xsl:apply-templates/></rectangle>
  </xsl:template>
  
</xsl:stylesheet>
