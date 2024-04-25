<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>HW 5.4</title>
            </head>
            <body>
                <h1>Company and Restaurants</h1>
                <table border="1">
                    <tr>
                        <th>CompanyName</th>
                        <th>Restaurants</th>
                    </tr>
                    <xsl:for-each select="ReviewApplication/Companys/Company">
                        <xsl:variable name="companyName" select="CompanyName"/>
                        <xsl:for-each select="Restaurants/Restaurant">
                            <tr>
                                <td><xsl:value-of select="$companyName"/></td>
                                <td><xsl:value-of select="Name"/></td>
                            </tr>
                        </xsl:for-each>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
