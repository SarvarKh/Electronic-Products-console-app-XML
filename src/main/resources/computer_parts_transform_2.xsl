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
            <xsl:if test="onSales = 'Y'">
                <span style="color: red;">[ON SALE!]</span>
            </xsl:if>
            <xsl:value-of select="name/text()" />
            <xsl:choose>
                <xsl:when test="availability &lt; 5">
                    <span style="color: red;">[RARE!]</span>
                </xsl:when>
                <xsl:otherwise>
                    <span style="color: red;">[PLENTY!]</span>
                </xsl:otherwise>
            </xsl:choose>
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
