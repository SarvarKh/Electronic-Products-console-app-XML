<?xml version="1.0" encoding="UTF-8"?>
<!--Transform the initial file into HTML table.-->
<xsl:stylesheet  version="2.0"
                 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 exclude-result-prefixes="xsi"
                 xmlns:xs="http://www.w3.org/2001/XMLSchema"
                 xmlns:my="http://example.com/my-namespace">
    <xsl:function name="my:color-product-name" as="element()">
        <xsl:param name="item" as="xs:string"/>
        <xsl:variable name="color">
            <xsl:choose>
                <xsl:when test="contains($item, 'Intel')">blue</xsl:when>
                <xsl:when test="contains($item, 'AMD')">red</xsl:when>
                <xsl:otherwise>black</xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:element name="span">
            <xsl:attribute name="style">
                <xsl:value-of select="concat('color:', $color)"/>
            </xsl:attribute>
            <xsl:value-of select="$item"/>
        </xsl:element>
    </xsl:function>

    <xsl:param name="outputType" select="html"/>
    <xsl:attribute-set name="category-attrs">
        <xsl:attribute name="products"></xsl:attribute>
    </xsl:attribute-set>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Computer spare parts - inventories</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Category</th>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Currency</th>
                        <th>Amount</th>
                        <th>Availability</th>
                        <th>OnSales</th>
                        <th>Recommended</th>
                    </tr>
                    <xsl:apply-templates select="categories"/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="categories">
        <xsl:apply-templates select="category"/>
    </xsl:template>
    <xsl:template match="category">
        <xsl:copy>
            <xsl:attribute name="products">
                <xsl:value-of select="count(products/product)"/>
            </xsl:attribute>
        </xsl:copy>
        <xsl:apply-templates select="products"/>
    </xsl:template>
    <xsl:template match="products">
        <xsl:apply-templates select="product"/>
    </xsl:template>
    <xsl:template match="product">
        <tr>
            <td><xsl:value-of select="ancestor::category/@type"/></td>
            <td>
                <xsl:number format="1. "/>
            </td>
            <td>
                <xsl:if test="onSales = 'Y'">
                    <span style="color: gray;">[ON SALE!]</span>
                </xsl:if>
                    <xsl:if test="not(name)">
                        <xsl:message terminate="yes">Error: Product without a name detected</xsl:message>
                    </xsl:if>
                <xsl:variable name="product-name" select="name/."/>
                <xsl:sequence select="my:color-product-name($product-name)"/>
                <xsl:choose>
                    <xsl:when test="availability &lt; 5">
                        <span style="color: gray;">[RARE!]</span>
                    </xsl:when>
                    <xsl:otherwise>
                        <span style="color: gray;">[PLENTY!]</span>
                    </xsl:otherwise>
                </xsl:choose>
            </td>
            <td>
                <xsl:apply-templates select="price/currency"/>
            </td>
            <td>
                <xsl:apply-templates select="price/amount"/>
            </td>
            <td><xsl:value-of select="availability/."/></td>
            <td><xsl:value-of select="onSales/."/></td>
            <td><xsl:value-of select="recommended/@value"/></td>
        </tr>
    </xsl:template>

    <xsl:template match="price/currency">
        <xsl:value-of select="text()"/>
    </xsl:template>
    <xsl:template match="price/amount">
        <xsl:value-of select="text()"/>
    </xsl:template>

<!--    <xsl:function name="my-function">-->
<!--        <xsl:param name="input"/>-->
<!--        <xsl:value-of select="concat('My message is: ', $input)"/>-->
<!--    </xsl:function>-->
</xsl:stylesheet>
