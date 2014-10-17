package com.googlecode.jvmvm.ui.levels.level_02;

import java.awt.*;

public abstract class Definition {
    public Color color = Color.LIGHT_GRAY;

    public String type = null;

    public char symbol = ' ';

    public void onCollision(Player player) {
    }

    public void onPickUp(Player player) {
    }

    public void onDrop() {
    }

    public void behavior(Me me) {
    }

    public boolean impassable = false;
}


/*
Objects can have the following parameters:
    color: '#fff' by default
    impassable: true if it blocks the player from movement (false by default)
    onCollision: function (player, game) called when player moves over the object
    onPickUp: function (player, game) called when player picks up the item
    symbol: Unicode character representing the object
    type: 'item' or null

        'empty' : {
        'symbol': ' ',
        'impassableFor': ['raft']
        },

        'player' : {
        'symbol': '@',
        'color': '#0f0'
        },

        'exit' : {
        'symbol' : String.fromCharCode(0x2395), // ⎕
        'color': '#0ff',
        'onCollision': function (player) {
        if (!game.map.finalLevel) {
        game._moveToNextLevel();
        }
        }
        },

        // obstacles

        'block': {
        'symbol': '#',
        'color': '#999',
        'impassable': true
        },

        'tree': {
        'symbol': '♣',
        'color': '#080',
        'impassable': true
        },

        'mine': {
        'symbol': ' ',
        'onCollision': function (player) {
        player.killedBy('a hidden mine');
        }
        },

        'trap': {
        'type': 'dynamic',
        'symbol': '*',
        'color': '#f00',
        'onCollision': function (player, me) {
        player.killedBy('a trap');
        },
        'behavior': null
        },

        'teleporter': {
        'type': 'dynamic',
        'symbol' : String.fromCharCode(0x2395), // ⎕
        'color': '#f0f',
        'onCollision': function (player, me) {
        if (!player._hasTeleported) {
        game._callUnexposedMethod(function () {
        player._moveTo(me.target);
        });
        }
        player._hasTeleported = true;
        },
        'behavior': null
        },

        // items

        'computer': {
        'type': 'item',
        'symbol': String.fromCharCode(0x2318), // ⌘
        'color': '#ccc',
        'onPickUp': function (player) {
        $('#editorPane').fadeIn();
        game.editor.refresh();
        game.map.writeStatus('You have picked up the computer!');
        },
        'onDrop': function () {
        $('#editorPane').hide();
        }
        },

        'phone': {
        'type': 'item',
        'symbol': String.fromCharCode(0x260E), // ☎
        'onPickUp': function (player) {
        game.map.writeStatus('You have picked up the function phone!');
        $('#phoneButton').show();
        },
        'onDrop': function () {
        $('#phoneButton').hide();
        }
        },

        'redKey': {
        'type': 'item',
        'symbol': 'k',
        'color': 'red',
        'onPickUp': function (player) {
        game.map.writeStatus('You have picked up a red key!');
        }
        },

        'greenKey': {
        'type': 'item',
        'symbol': 'k',
        'color': '#0f0',
        'onPickUp': function (player) {
        game.map.writeStatus('You have picked up a green key!');
        }
        },

        'blueKey': {
        'type': 'item',
        'symbol': 'k',
        'color': '#06f',
        'onPickUp': function (player) {
        game.map.writeStatus('You have picked up a blue key!');
        }
        },

        'yellowKey': {
        'type': 'item',
        'symbol': 'k',
        'color': 'yellow',
        'onPickUp': function (player) {
        game.map.writeStatus('You have picked up a yellow key!');
        }
        },

        'theAlgorithm': {
        'type': 'item',
        'symbol': 'A',
        'color': 'white',
        'onPickUp': function (player) {
        game.map.writeStatus('You have picked up the Algorithm!');
        },
        'onDrop': function () {
        game.map.writeStatus('You have lost the Algorithm!');
        }
        }
*/
