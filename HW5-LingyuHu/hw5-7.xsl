<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:key name="restaurant-recommendations" match="ReviewApplication/Recommendations/Recommendation" use="RestaurantId"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>HW 5.7</title>
            </head>
            <body>
                <h1>Restaurants and Recommendations</h1>
                <table border="1">
                    <tr>
                        <th>Restaurant</th>
                        <th>Recommendations</th>
                    </tr>
                    <xsl:for-each select="ReviewApplication/Companys/Company/Restaurants/Restaurant">
                        <xsl:variable name="currentRestaurantId" select="RestaurantId"/>
                        <tr>
                            <td><xsl:value-of select="Name"/></td>
                            <td>
                                <xsl:value-of select="count(//Recommendation[RestaurantId=$currentRestaurantId])"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
