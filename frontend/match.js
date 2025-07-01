const apiBaseUrl = 'http://localhost:8080/api/matches'; // Update if needed

document.getElementById('matchForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent default form submission

    const seriesId = document.getElementById('seriesId').value;
    const teamA = document.getElementById('teamA').value;
    const teamB = document.getElementById('teamB').value;
    const overs = document.getElementById('overs').value;
    const tossWinner = document.getElementById('tossWinner').value;
    const choice = document.getElementById('choice').value;
    const currentBattingTeam = document.getElementById('currentBattingTeam').value;
    const striker = document.getElementById('striker').value;
    const nonStriker = document.getElementById('nonStriker').value;
    const currentBowler = document.getElementById('currentBowler').value;

    const matchData = {
        seriesId: parseInt(seriesId),
        teamA,
        teamB,
        overs: parseInt(overs),
        tossWinner,
        choice,
        currentBattingTeam,
        striker,
        nonStriker,
        currentBowler
    };

    try {
        const response = await fetch(apiBaseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(matchData)
        });

        const messageElement = document.getElementById('message');

        if (response.ok) {
            const result = await response.json();
            messageElement.innerText = "Match created successfully! Match ID: " + result.id;
            messageElement.style.color = "green";
            window.location.href = 'livescore.html';
        } else {
            const error = await response.text();
            messageElement.innerText = "Error creating match: " + error;
            messageElement.style.color = "red";
        }
    } catch (error) {
        console.error("Error:", error);
        document.getElementById('message').innerText = "Error connecting to server.";
        document.getElementById('message').style.color = "red";
    }
});
