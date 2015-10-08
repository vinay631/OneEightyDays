"use strict";

var HangmanWord = React.createClass({
    displayName: "HangmanWord",

    render: function render() {
        return React.createElement(
            "div",
            { className: "container" },
            React.createElement(
                "section",
                { className: "header" },
                React.createElement(
                    "h1",
                    { className: "title" },
                    "Game of Hangman."
                ),
                React.createElement(
                    "button",
                    { className: "button-primary" },
                    "Start Game"
                )
            ),
            React.createElement(
                "div",
                { className: "container" },
                React.createElement(
                    "div",
                    { className: "value-props row" },
                    React.createElement(
                        "div",
                        { className: "twelve column" },
                        React.createElement("img", { className: "img-80x", src: "assets/images/tree7.png" })
                    )
                )
            )
        );
    }

});
React.render(React.createElement(HangmanWord, null), document.getElementById('gamecontent'));