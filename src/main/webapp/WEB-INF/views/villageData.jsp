<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/villageData.css">

    <title>Village Data</title>
</head>
<body>
    <div class="main"><p style="font-family: Cormorant Garamond Light;color:rgba(64,66,51,1);font-size:47px">Village Information</p></div>
    
    <div class="box">
    <ul>    <li>Village Name: ${village.villageName}</li>
            <li>Population: ${village.population}</li>
            <li>Male Population: ${village.male}</li>
            <li>Female Population: ${village.female}</li>
            <li>Children: ${village.children}</li>
            <li>Population Density: ${village.populationDensity}</li>
            <li>Sex Ratio: ${village.sexRatio}</li>
            <li>Literacy Rate: ${village.literacyRate}</li>
            <li>Female Literacy Rate: ${village.femaleLiteracyRate}</li>
            <li>Illiterates: ${village.illiterates}</li>
            <li>Working Population Percentage: ${village.workingPopulationPercentage}</li>
            <li>District: ${village.district}</li>
            <li>Division: ${village.division}</li>
            <li>Altitude: ${village.altitude}</li>
            <li>Telephone/STD Code: ${village.telephoneStdCode}</li>
            <li>Local Language: ${village.localLanguage}</li>
            <li>Pin Code: ${village.pinCode}</li>
            <li>National Highways: ${village.nationalHighways}</li>
            <li>Political Representation: ${village.politics}</li>
            <li>Schools: ${village.schools}</li>
            <li>Colleges: ${village.colleges}</li>
            <li>Health Centres/Hospitals: ${village.healthCentresHospitals}</li>
            <li>Total Geographical Area: ${village.totalGeographicalArea}</li>
            <li>Agricultural Area: ${village.agriculturalArea}</li>
            <li>Irrigated Land: ${village.irrigatedLand}</li>
            <li>Non-Agricultural Area: ${village.nonAgriculturalArea}</li>
            <li>Agricultural Commodities: ${village.agriculturalCommodities}</li>
            <li>Drinking Water: ${village.drinkingWater}</li>
            <li>Sanitation: ${village.sanitation}</li>
            <li>Postal Head Office: ${village.postalHeadOffice}</li>
        </ul>
    </div>
           <div class="green">
            <span class="text">Village Connect</span>
            <h1 class="address">+91 9131630631</h1><br>
                <h1 class="address">
                    <a href="mailto:tanushreebaingne20@gmail.com">tanushreebaingne20@gmail.com</a>
                </h1><br><br>
                <h1 class="address">Ayodhya Nagar,  Bhopal,  Madhya Pradesh</h1>
        </div>
</body>
</html>
