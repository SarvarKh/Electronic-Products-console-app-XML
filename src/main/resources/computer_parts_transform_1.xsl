<?xml version="1.0" encoding="UTF-8"?>
<!--Transform the initial file into HTML table.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="outputType" select="html"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Computer spare parts - inventories</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Category</th>
                        <th>Name</th>
                        <th>Currency</th>
                        <th>Amount</th>
                        <th>Availability</th>
                        <th>OnSales</th>
                        <th>Recommended</th>
                    </tr>
                    <xsl:for-each select="categories/category">
                        <xsl:for-each select="products/product">
                            <tr>
                                <td><xsl:value-of select="ancestor::category/@type"/></td>
                                <td><xsl:value-of select="name/."/></td>
                                <td><xsl:value-of select="price/currency/."/></td>
                                <td><xsl:value-of select="price/amount/."/></td>
                                <td><xsl:value-of select="availability/."/></td>
                                <td><xsl:value-of select="onSales/."/></td>
                                <td><xsl:value-of select="recommended/@value"/></td>
                            </tr>
                        </xsl:for-each>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
