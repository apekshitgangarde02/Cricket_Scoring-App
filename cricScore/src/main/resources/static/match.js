// match.js

const matchId = localStorage.getItem("matchId") || prompt("Enter Match ID:");

async function submitBall() {
    const striker = document.getElementById("striker").value;
    const bowler = document.getElementById("bowler").value;
    const runs = parseInt(document.getElementById("runs").value);
    const result = document.getElementById("result").value;
    const bouncer = document.getElementById("bouncer").checked;

    let wide = result === "Wide";
    let noBall = result === "NoBall";
    let freeHit = false;  // We'll assume freeHit handled by backend for now.

    // Construct ball summary object
    const ballSummary = {
        strikerName: striker,
        bowlerName: bowler,
        runs: runs,
        wide: wide,
        noBall: noBall,
        freeHit: freeHit,
        bouncer: bouncer,
        result: result
    };

    try {
        const response = await fetch(`/api/matches/${matchId}/ball-summary`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(ballSummary),
        });

        if (!response.ok) {
            throw new Error("Failed to submit ball");
        }

        const updatedMatch = await response.json();
        displayMatchInfo(updatedMatch);

        // Clear fields after submission
        document.getElementById("runs").value = "";
        document.getElementById("result").value = "Run";
        document.getElementById("bouncer").checked = false;

    } catch (error) {
        alert("Error updating ball: " + error.message);
    }
}

function displayMatchInfo(match) {
    const info = `
        <strong>Score:</strong> ${match.scoreA}/${match.wicketsA}<br>
        <strong>Overs:</strong> ${match.currentOver}.${match.currentBall} balls<br>
        <strong>Batting:</strong> ${match.battingTeam}<br>
        <strong>Bowling:</strong> ${match.bowlingTeam}
    `;
    document.getElementById("matchInfo").innerHTML = info;
}
