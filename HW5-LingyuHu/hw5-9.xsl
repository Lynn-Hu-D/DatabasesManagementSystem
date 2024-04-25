<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>HW 5.9</title>
            </head>
            <body>
                <h1>Reviews with Recommendations</h1>
                <table border="1">
                    <tr>
                        <th>RestaurantId</th>
                        <th>UserName</th>
                        <th>Rating</th>
                        <th>Recommendations</th>
                    </tr>
                    <xsl:for-each select="ReviewApplication/Reviews/Review">
                        <xsl:variable name="currentRestaurantId" select="RestaurantId"/>
                        <xsl:variable name="currentUserName" select="UserName"/>
                        <tr>
                            <td><xsl:value-of select="$currentRestaurantId"/></td>
                            <td><xsl:value-of select="$currentUserName"/></td>
                            <td><xsl:value-of select="Rating"/></td>
                            <td>
                                <xsl:value-of select="count(//Recommendations/Recommendation[RestaurantId=$currentRestaurantId and UserName=$currentUserName])"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
