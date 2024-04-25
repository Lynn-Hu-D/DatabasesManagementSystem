<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>HW 5.10</title>
            </head>
            <body>
                <h1>Reviews with Details</h1>
                <table border="1">
                    <tr>
                        <th>RestaurantName</th>
                        <th>FirstName</th>
                        <th>Rating</th>
                    </tr>
                    <xsl:for-each select="ReviewApplication/Reviews/Review">
                        <xsl:variable name="currentRestaurantId" select="RestaurantId"/>
                        <xsl:variable name="currentUserName" select="UserName"/>
                        <tr>
                            <td>
                                <xsl:value-of select="/ReviewApplication/Companys/Company/Restaurants/Restaurant[RestaurantId=$currentRestaurantId]/Name"/>
                            </td>
                            <td>
                                <xsl:value-of select="/ReviewApplication/Users/User[UserName=$currentUserName]/FirstName"/>
                            </td>
                            <td><xsl:value-of select="Rating"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
