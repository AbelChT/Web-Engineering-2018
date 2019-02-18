const express = require('express');

const swaggerUi = require('swagger-ui-express');
const swaggerDocument = require('./API/AddressBook.json');

const app = express();

const options = {
    explorer : true
};

app.use('/docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument, options));

module.exports = app;
