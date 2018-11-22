# What's new
- The API have been specified with Swagger and a Swagger UI instance have been added to the project to try it.
- The project have been modified to allow CORS.

# Where is the open api specification?
The open api specification is located at swagger-api/API

# How you can try it
## Requirements
- Nodejs
- npm

## Steps
First of all clone the repo.
```
$ git clone https://github.com/AbelChT/lab3-restful-ws.git
$ cd lab3-restful-ws
$ git checkout bonus
```

The next step is to open the server in one terminal.
```
$ ./gradlew serve
```

Now open other terminal, there we will execute the Swagger UI server.
```
$ cd lab3-restful-ws
$ cd swagger-api
$ npm start
```

After both process being runing, you can open the address localhost:3000/docs in your browser and try it.