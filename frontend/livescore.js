const apiBaseUrl = "http://localhost:8080/api/matches";

let createdMatchId = null;

// Create Match
document.getElementById('matchForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const teamA = document.getElementById('teamA').value;
    const teamB = document.getElementById('teamB').value;
    const venue = document.getElementById('venue').value;

    const response = await fetch(apiBaseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            teamA: teamA,
            teamB: teamB,
            venue: venue
        })
    });

    if (response.ok) {
        const match = await response.json();
        createdMatchId = match.id;

        document.getElementById('matchForm').style.display = 'none';
        document.getElementById('matchCreated').style.display = 'block';

        document.getElementById('matchInfo').innerText = `Match ID: ${match.id}, ${match.teamA} vs ${match.teamB} at ${match.venue}`;
    } else {
        document.getElementById('message').innerText = "❌ Failed to create match.";
        document.getElementById('message').style.color = "red";
    }
});

// Set Toss
document.getElementById('tossForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const tossWinner = document.getElementById('tossWinner').value;
    const choice = document.getElementById('choice').value;

    const response = await fetch(`${apiBaseUrl}/${createdMatchId}/toss?tossWinner=${tossWinner}&choice=${choice}`, {
        method: 'PUT'
    });

    if (response.ok) {
        alert('✅ Toss information updated!');
    } else {
        alert('❌ Failed to update toss info.');
    }
});

// Add Ball Summary
document.getElementById('ballForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const runs = parseInt(document.getElementById('runs').value, 10);
    const result = document.getElementById('result').value;
    const wide = document.getElementById('wide').checked;
    const noBall = document.getElementById('noBall').checked;

    const response = await fetch(`${apiBaseUrl}/${createdMatchId}/ball-summary`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            runs: runs,
            result: result,
            wide: wide,
            noBall: noBall
        })
    });

    if (response.ok) {
        alert('✅ Ball Summary Updated!');
    } else {
        alert('❌ Failed to update ball summary.');
    }
});

async function submitBall() {
    const runs = document.getElementById('runs').value;
    const isWicket = document.getElementById('isWicket').checked;

    const ballData = {
        runs: parseInt(runs),
        isWicket: isWicket
    };

    try {
        const response = await fetch('http://localhost:8080/api/matches/{matchId}/ball', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ballData)
        });

        if (response.ok) {
            console.log('Ball submitted successfully');
            // update UI if needed
        } else {
            console.error('Error submitting ball');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
