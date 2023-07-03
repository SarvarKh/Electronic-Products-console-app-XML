<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                exclude-result-prefixes="xsi">
    <xsl:param name="outputType" select="xml"/>
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <products>
            <xsl:apply-templates select="categories/category/products/product" />
        </products>
    </xsl:template>
    <xsl:template match="product">
        <product>
            <xsl:copy-of select="*"/>
            <category>
                <xsl:value-of select="ancestor::category/@type"/>
            </category>
        </product>
    </xsl:template>
</xsl:stylesheet>