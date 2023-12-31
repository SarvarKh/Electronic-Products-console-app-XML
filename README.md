# Electronic-Products-console-app-XML
This is a console application project based on Electronic Products XML data. It can validate, style, transform, query data from database.

## Screenshots

<div style="display: flex; flex-wrap: wrap">
<div align="center">
  <img src="image/1_console.png?raw=true" width="90%" height="auto"/>
  <img src="image/2_computers_html.png?raw=true" width="45%" height="auto"/>
  <img src="image/3_computers_html.png?raw=true" width="45%" height="auto"/>
</div>
</div>


## Getting Started
To get started with this project, follow these steps:

- Clone the repository to your local machine.
- Install in pom.xml file.
- Run the application using your IDEA's run button on Main class.
- Console window will be opened.
- you can play around with this interactive console to retrieve all inventories or by their parameters;

## Running application
- open ReadXMLWithSAX class and run main method, and interact with console. OR
- open your terminal and from root project directory run: mvn exec:java -Dexec.mainClass="net.sf.saxon.Transform" -Dexec.args="-s:src/main/resources/computer_parts.xml -xsl:src/main/resources/computer_parts_transform_1_util.xsl -o:outputTEST2.html"

## Features
This project includes the following features:

- Validate XML file with DTD or XSD.
- Transform content data of XML file into separate Text file using XSLT.
- XQuery data from Postgresql.


## Technologies Used
This project was built using the following technologies:

- Java 17.0.2 (Servlets, Filters, Listeners)
- XML, XSLT, XPATH, DTD, XSD, DOM, SAX
- Maven
- Postgresql
- BaseX (XML database engine and a highly compliant XQuery 3.1 processor)
- Saxon-HE (open-source versions of the Saxon XSLT 3.0, XQuery 3.1, and XPath 3.1 processor for Java)

## Authors

👤 **Sarvar Khalimov**

- GitHub: [SarvarKh](https://github.com/SarvarKh)
- Twitter: [KhalimovSarvar](https://twitter.com/KhalimovSarvar)
- LinkedIn: [Sarvar-Khalimov](https://www.linkedin.com/in/sarvar-khalimov/)


## Contributing
If you would like to contribute to this project, please follow these steps:

- Fork the repository.
- Create a new branch for your feature or bug fix.
- Make your changes and commit them.
- Push your changes to your fork.
- Submit a pull request.

## Show your support
Give a ⭐️ if you like this project!

## License
This project is [MIT](./MIT.md) licensed.