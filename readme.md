# Aerotool - Aircraft maintanance manuals, materials and tool management software

## Description
The Aerotool project emerged from the interest of students currently in the final year of the Technical Course in Internet Computing (TII) integrated with High School, under the guidance of the Professor Jorge Francisco Cutigi and Professor Lucas Bueno R. de Oliveira. It consists of a tool request management system for didactic purposes, where teachers request tool loans through requests to the responsible technician, who approves or denies them. The system promotes greater traceability of the tools and prevents losses or theft of items. Currently, the tool module is in the process of development. In addition to these, future updates are planned to implement Aircraft Management and Warehouse Control modules.

## Target Audience
### Institute Federal de São Paulo - São Carlos Campus
An educational institution that hosts the educational project for the development of the software, which is used by teachers and education professionals to assist graduating students in aircraft maintenance and processes.

### Applied Aircraft Management Services
The system is used on an enterprise scale for the management of aircraft and their processes, including tools, consumable materials, and project management.

## Objectives
* Increase tool traceability;
* Prevent losses and theft;
* Enhance clarity in communication and class organization.

## Technologies
Front-end  | Back-end
------------- | -------------
React (JavaScript)  | Spring Boot (Java)
Next.js  | PostgreSQL
Redux and React-Query (Global Management) |
Figma |
Tailwind |

## Development Team
### See below the developers of the Aerotool software:
- `Arthur Vicente Mascaro`
- `Gustavo Rodrigues Trizotti`
- `Heitor Leite de Almeida`
- `Renan Trofino Silva`
- `Miguel Santos Lordello`

### See below the involved advisors:
- `Jorge Francisco Cutigi`
- `Lucas Bueno R. de Oliveira`

## Application Setup
### Creating the Database

In IntelliJ Ultimate, open the database tab and connect to your machine's postgresql database.

Then go to the directory postgre/sql where you'll execute the following files:
- ``V000_setup.sql``
- ``V001_create_schema.sql``
- ``V002_populate_schema.sql``

Afterwards, select and run all the code in ``V000_setup.sql`` in a session from the database connection you just made. After successfully running the code in this file you'll have created a database called "aerotool" with an owner "aerotool-app". You should be able to verify these changes in pgAdmin4.

Now connect to the "aerotool" database in the same way you connected to the postgresql one. Afterwards, select and run all the code in ``V001_create_schema.sql`` in a session in the DB connection you just made. Then repeat the same proccess with the ``V002_populate_schema.sql`` file.

### Running the Application

After making sure your database is set up and populated, you can run the java file ``src/main/java/edu/br/ifsp/AerotoolApplication.java``
