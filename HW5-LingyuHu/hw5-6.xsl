<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>HW 5.6</title>
            </head>
            <body>
                <h1>username5 Recommendations</h1>
                <table border="1">
                    <tr>
                        <th>UserName</th>
                        <th>Recommendations</th>
                    </tr>
                    <tr>
                        <td>username5</td>
                        <td>
                            <xsl:value-of select="count(/ReviewApplication/Recommendations/Recommendation[UserName='username5'])"/>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
