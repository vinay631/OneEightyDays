"use strict";

var HangmanWord = React.createClass({
    displayName: "HangmanWord",

    getInitialState: function getInitialState() {
        return { word: "",
            message: "",
            numberOfMisses: 0,
            won: false,
            lost: false };
    },

    render: function render() {
        return React.createElement(
            "div",
            { className: "container" },
            React.createElement(
                "section",
                { className: "headermain" },
                React.createElement(
                    "h1",
                    { className: "title" },
                    "Game of Hangman."
                ),
                React.createElement(
                    "button",
                    { className: "button-primary", onClick: this.startGameClicked },
                    "Start Game"
                )
            ),
            React.createElement(
                "div",
                { className: "headermain" },
                React.createElement("img", { className: "img-60x", src: "assets/images/tree" + this.state.numberOfMisses.toString() + ".png" })
            ),
            React.createElement(
                "div",
                { className: "headermain" },
                React.createElement(
                    "span",
                    null,
                    this.state.word
                )
            ),
            React.createElement(
                "div",
                { className: "headermain" },
                React.createElement(
                    "div",
                    { "class": "row" },
                    React.createElement(
                        "div",
                        { "class": "three columns" },
                        React.createElement(
                            "span",
                            { className: "light-keys keys", onClick: this.guessClick },
                            React.createElement(
                                "button",
                                null,
                                "Q"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "W"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "E"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "R"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "T"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "Y"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "U"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "I"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "O"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "P"
                            )
                        )
                    )
                ),
                React.createElement(
                    "div",
                    { "class": "row" },
                    React.createElement(
                        "div",
                        { "class": "three columns" },
                        React.createElement(
                            "span",
                            { className: "light-keys keys", onClick: this.guessClick },
                            React.createElement(
                                "button",
                                null,
                                "A"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "S"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "D"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "F"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "G"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "H"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "J"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "K"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "L"
                            )
                        )
                    )
                ),
                React.createElement(
                    "div",
                    { "class": "row" },
                    React.createElement(
                        "div",
                        { "class": "three columns" },
                        React.createElement(
                            "span",
                            { className: "light-keys keys", onClick: this.guessClick },
                            React.createElement(
                                "button",
                                null,
                                "Z"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "X"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "C"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "V"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "B"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "N"
                            ),
                            " ",
                            React.createElement(
                                "button",
                                null,
                                "M"
                            )
                        )
                    )
                )
            )
        );
    },

    startGameClicked: function startGameClicked() {
        return $.ajax({
            url: "/start",
            dataType: 'json',
            cache: false,
            success: (function (data) {
                this.setState({ word: data["word"],
                    message: data["message"],
                    numberOfMisses: data["misses"],
                    won: data["won"],
                    lost: data["lost"] });
            }).bind(this),
            error: (function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }).bind(this)
        });
    },

    guessClick: function guessClick(event) {
        if (this.state.word !== "") {
            return $.ajax({
                url: "/guess/" + event.target.innerHTML,
                dataType: 'json',
                cache: false,
                success: (function (data) {
                    this.setState({ word: data["word"],
                        message: data["message"],
                        numberOfMisses: data["misses"],
                        won: data["won"],
                        lost: data["lost"] });
                }).bind(this),
                error: (function (xhr, status, err) {
                    console.error(this.props.url, status, err.toString());
                }).bind(this)
            });
        }
        ;
    }

});

React.render(React.createElement(HangmanWord, { imagenum: "1" }), document.getElementById('gamecontent'));