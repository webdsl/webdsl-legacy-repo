<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
<xsl:template match="methodCall" xml:space="preserve">
<methodResponse>
  <params>
    <param>
      <value>
        <string>
          <xsl:value-of select="params/param/value" />
        </string>
      </value>
    </param>
  </params>
</methodResponse>
</xsl:template>

</xsl:stylesheet>
