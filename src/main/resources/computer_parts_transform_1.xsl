<?xml version="1.0" encoding="UTF-8"?>
<!--Transform the initial file into HTML table.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="computer_parts_transform_1_util.xsl"/>
    <xsl:param name="outputType" select="html"/>

    <xsl:template match="/">
        <xsl:apply-imports/>
    </xsl:template>
</xsl:stylesheet>
