## <p align="center">Spring Boot Application</p>

****
### Tech stack and tools used

* Java
* Spring boot
* Postgres
* Rest API
* Maven

### Overview

A Spring Boot application that manages entities related to persons, identity proofs, and projects. It provides RESTful
endpoints for CRUD operations on these entities.

### Usage

To run this Spring Boot application locally, follow these steps:

1. Clone the
   repository&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `git clone https://github.com/PavanReddy0604/Spring-boot-Monolith.git`
2. Navigate to the project directory&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`cd <project-directory>`
3. Build the project&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  `./mvnw clean package`
4. Run the application&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `java -jar target/<project-name>.jar`

### Idea based on which this project is developed

- IdProof and Person Entities have One To One mapping between them that means, We can't have multiple IdProofs for a
  single person and the person can not have more than one same IdProof.
- Person and Project Entities have One To Many Mapping between them that means, One person can make multiple projects.

### APIs

- [IdProof](#IdProof)
- [Project](#Project)

#### <ins>IdProof</ins>

- [Save IdProof](#save-idproof)
- [Get all IdProofs](#get-all-idproofs)
- [Get IdProof By ProofName](#get-idproof-by-poofname)
- [Update IdProof](#update-idproof)
- [Delete IdProof](#delete-idproof)

#### <ins>Project</ins>

- [Save Project](#save-project)
- [Get all Projects](#get-all-projects)
- [Get all Projects By Person](#get-all-projects-by-person)
- [Update Project](#update-project)
- [Delete Project](#delete-all-projects-of-a-person)

##### <ins>Save IdProof</ins>

> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; POST  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /idProof/  
> Content-Type: application/json

**Request Body**

```json
{
  "proofName": "Adhar card",
  "person": {
    "personName": "Pavan Kumar Reddy",
    "gender": "MALE",
    "mobileNumber": 10000000
  }
}
```

**Response**

```diff
Id of the saved IdProof
```

##### <ins>Get all IdProofs</ins>

> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; GET  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /idProof/

**Request Body**

- No Request body is required

**Response Body**

```json
[
  {
    "proofId": "4e6d0a72-3e2c-4d8f-aa87-f70e6f61ea18",
    "proofName": "Adhar card",
    "person": {
      "personId": "a32667a3-9f64-48a5-962c-79de8ec0392c",
      "personName": "Pavan Kumar Reddy",
      "gender": "MALE",
      "mobileNumber": 10000000,
      "project": []
    }
  }
]
```

##### <ins>Get IdProof by PoofName</ins>

> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; GET  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /idProof/proofName/{proofName}

**Request Body**

- No Request body is required

**Response Body**

```json
[
  {
    "proofId": "4e6d0a72-3e2c-4d8f-aa87-f70e6f61ea18",
    "proofName": "Adhar card",
    "person": {
      "personId": "a32667a3-9f64-48a5-962c-79de8ec0392c",
      "personName": "Pavan Kumar Reddy",
      "gender": "MALE",
      "mobileNumber": 10000000,
      "project": []
    }
  }
]
```

##### <ins>Update IdProof</ins>

> Method&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; PUT  
> EndPoint&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
> /idProof/  
> Content-Type&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;application/json>  

**Request Body**

```json
  {
  "proofId": "f8088b94-a08c-4c53-b5e7-1663954794af",
  "proofName": "Adhar",
  "person": {
    "personId": "907ef321-1f56-48b3-856b-d4e8f5a5e806",
    "personName": "Pavan Kumar Reddy",
    "gender": "MALE",
    "mobileNumber": 9704002726232
  }
}
  ```

**Response Body**

``` diff
Id of the updated IdProof
```

##### <ins>Delete IdProof</ins>

> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DELETE  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /idProof/{proofId}

**Request Body**

- No Request body is required

**Response Body**

``` diff
Id of the deleted IdProof
```

##### <ins>Save Project</ins>
> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; POST  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /project/  
> Content-Type: application/json  
> Request Params : personName,mobileNumber

**Request Body**

```json
{
  "projectName": "Springboot Monolithic application",
  "projectDescription": "This project helps to understand how a monolithic Springboot application can be built where an Entity has relation with other Entities",
  "projectType": "MINOR"
}
```

**Response**

```diff
Id of the saved Project
```
##### <ins>Get all Projects</ins>
> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; GET  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /project/  

**Request Body**
> No Request Body is required

**Response Body**
```json
[
    {
        "projectName": "Springboot Monolithic application",
        "projectDescription": "This project helps to understand how a monolithic Springboot application can be built where an Entity has relation with other Entities",
        "projectType": "MINOR"
    }
]
```

##### <ins>Get all Projects by Person</ins>
> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; GET  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /project  
> Request Params : personName,mobileNumber

**Request Body**
> No Request Body is required

**Response Body**
```json
[
    {
        "projectName": "Springboot Monolithic application",
        "projectDescription": "This project helps to understand how a monolithic Springboot application can be built where an Entity has relation with other Entities",
        "projectType": "MINOR"
    }
]
```

##### <ins>Update Project</ins>
> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; PUT  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /project/{projectId}

**Request Body**
- The project details you want to update (Here I have changed projectType to MAJOR)
```json
{
    "projectName": "Springboot Monolithic application",
    "projectDescription": "This project helps to understand how a monolithic Springboot application can be built where an Entity has relation with other Entities",
    "projectType": "MAJOR"
}
```
**Response Body**
> Id of the updated Project

##### <ins>Delete all Projects of a Person</ins>
> Method :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DELETE  
> EndPoint :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /project  
> Request Params : personName,mobileNumber

**Request Body**
> No request body is required

**Response Body**
> No response body








