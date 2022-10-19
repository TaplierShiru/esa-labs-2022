<?xml version="1.0" ?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="3.0"
>
    <xsl:output method="html" indent="yes" media-type="text/html" encoding="UTF-8" />
    <xsl:template match="/">
        <html>
        <head>
            <title>API XSLT</title>
        <body>
        <h1>Test XSLT Transformation</h1>
        <xsl:apply-templates />
        </body>
        </head>
        <style>
            table, th, td {
                border: 1px solid black;
            }
        </style>
        </html>
    </xsl:template>
    <xsl:template match="ArrayList">
        <h2>List of disciplines</h2>
        <table>
            <tr>
                <th>Name</th>
                <th>Code</th>
                <th>TypePass</th>
            </tr>
            <xsl:for-each select="item">
                <tr>
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="code"/></td>
                    <td><xsl:value-of select="typePass"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
