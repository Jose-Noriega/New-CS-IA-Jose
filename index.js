const express = require("express");
const { google } = require("googleapis");

const app = express();
const {readFileSync, promises: fsPromises} = require('fs');

function syncReadFile(filename) {
    const contents = readFileSync(filename, 'utf-8');
  
    const arr = contents.split(/\r?\n/);
  
    console.log(arr);
  
    return arr;
  }

app.get("/", async (req, res) => {

    arr = syncReadFile('./present.txt');  

    const auth = new google.auth.GoogleAuth({
        keyFile: "credentials.json",
        scopes: "https://www.googleapis.com/auth/spreadsheets",
    });

    const client = await auth.getClient();

    const googleSheets = google.sheets({ version: "v4", auth: client });

    const spreadsheetID = "1qnpftUHzHwmoOmtmvw7NchCIcMKNBtv7QCKuKNY1tHg"

    //adding to sheet
    await googleSheets.spreadsheets.values.append({
        auth,
        spreadsheetID,
        range: "Sheet1",
        valueInputOption: "USER_ENTERED",
        resource: {
            values: [[arr]],
        },
    })

    res.send("Done")
})

app.listen(1337, (req, res) => console.log("running on 1337"))