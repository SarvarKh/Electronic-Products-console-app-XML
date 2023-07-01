<?xml version="1.0" encoding="UTF-8"?>
<!--Transform the initial file into HTML multi-leveled list.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="outputType" select="html"/>
    <xsl:template match="/">
        <html>
            <body>
                <ul>
                    <xsl:apply-templates select="categories/category" />
                </ul>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="category">
        <li>
            <xsl:value-of select="@type" />
            <ul>
                <xsl:apply-templates select="products/product" />
            </ul>
        </li>
    </xsl:template>
    <xsl:template match="product">
        <li>
            <xsl:value-of select="name/text()" />
        </li>
        <li>
            <xsl:value-of select="price/currency/text()" />
        </li>
        <li>
            <xsl:value-of select="price/amount/text()" />
        </li>
        <li>
            <xsl:value-of select="availability/text()" />
        </li>
        <li>
            <xsl:value-of select="onSales/text()" />
        </li>
        <li>
            <xsl:value-of select="recommended/@value" />
        </li>
        <br/>
    </xsl:template>
</xsl:stylesheet>
