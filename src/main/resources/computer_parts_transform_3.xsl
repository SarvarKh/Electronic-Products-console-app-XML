<?xml version="1.0" encoding="UTF-8"?>
<!--Transform the initial file into plain text file.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="outputType" select="txt"/>
    <xsl:output method="text" indent="yes"/>

    <xsl:template match="/">
        <xsl:for-each select="categories/category">
            <xsl:value-of select="@type"/>
            <xsl:text>&#xA;</xsl:text>
            <xsl:for-each select="products/product">
                <xsl:value-of select="name/."/>
                <xsl:value-of select="price/currency/."/>
                <xsl:value-of select="price/amount/."/>
                <xsl:value-of select="availability/."/>
                <xsl:value-of select="onSales/."/>
                <xsl:value-of select="recommended/@value"/>
                <xsl:text>&#xA;</xsl:text>
            </xsl:for-each>
            <xsl:text>&#xA;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
