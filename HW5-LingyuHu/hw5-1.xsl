<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>HW 5.1</title>
            </head>
            <body>
                <h1>Companies and About</h1>
                <table border="1">
                    <tr>
                        <th>CompanyName</th>
                        <th>About</th>
                    </tr>
                    <xsl:for-each select="ReviewApplication/Companys/Company">
                        <tr>
                            <td><xsl:value-of select="CompanyName"/></td>
                            <td><xsl:value-of select="About"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
