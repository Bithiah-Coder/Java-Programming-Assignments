<%@ page import="java.util.*" %>
<%
    // Number of questions
    int numQuestions = 5;

    // Initialize session variables
    if (session.getAttribute("questions") == null) {
        Random rand = new Random();
        int[][] qs = new int[numQuestions][2];
        for (int i = 0; i < numQuestions; i++) {
            qs[i][0] = rand.nextInt(51); // first number
            qs[i][1] = rand.nextInt(51); // second number
        }
        session.setAttribute("questions", qs);
        session.setAttribute("currentIndex", 0);
        session.setAttribute("score", 0);
    }

    int[][] questions = (int[][]) session.getAttribute("questions");
    int currentIndex = (Integer) session.getAttribute("currentIndex");
    int score = (Integer) session.getAttribute("score");

    // Process previous answer
    String ansStr = request.getParameter("answer");
    if (ansStr != null && currentIndex > 0) {
        try {
            int userAnswer = Integer.parseInt(ansStr);
            int correctAnswer = questions[currentIndex - 1][0] + questions[currentIndex - 1][1];
            if (userAnswer == correctAnswer) {
                score++;
                session.setAttribute("score", score);
            }
        } catch (NumberFormatException e) {
            // invalid input, ignore
        }
    }

%>
<html>
<head>
    <title>Addition Quiz</title>
</head>
<body>
<h2>Addition Quiz</h2>

<%
    if (currentIndex < numQuestions) {
%>
    <form method="post">
        <%
            int a = questions[currentIndex][0];
            int b = questions[currentIndex][1];
        %>
        <p>Question <%= (currentIndex + 1) %>: <%= a %> + <%= b %> = ?</p>
        <input type="text" name="answer" required />
        <input type="submit" value="Next" />
    </form>
<%
        session.setAttribute("currentIndex", currentIndex + 1);
    } else {
%>
    <p>Quiz Completed!</p>
    <p>Your score: <%= score %> / <%= numQuestions %></p>
    <form method="post">
        <input type="submit" value="Restart Quiz" />
    </form>
<%
        session.invalidate(); // reset for new quiz
    }
%>
</body>
</html>
