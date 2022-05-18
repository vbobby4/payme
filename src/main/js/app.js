'use strict';

const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
import Donat from "./donat";
ReactDOM.render(
    <Donat/>,
    document.getElementById('root')
)