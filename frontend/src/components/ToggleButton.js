import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import '../styles/button.css';

const SIZES = ['btn-small', 'btn-medium', 'btn-large'];
const STYLES = ['', 'btn-round-blue', 'btn-text-blue', 'btn-text-selected'];

export const ToggleButton = ({children, btnSize, btnStyle, className, isLink, isHashLink, onClick, to}) => {

    const checkBtnSize = SIZES.includes(btnSize) ? btnSize : SIZES[0];
    const checkBtnStyle = STYLES.includes(btnStyle) ? btnStyle : STYLES[0];
    const checkTo = to == null ? '/' : to;
    const checkClassName = className == null ? '' : className;

    const [currText, setCurrentText] = useState({count: 0, text : children[0]});

    function handleOnClick(){

        setCurrentText((prevState) => {
            return{
                count: prevState.count + 1,
                text: children[(prevState.count + 1) % children.length]
            }
        });


        onClick();
    }

    if(isLink){
        return(
            <Link className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} to={checkTo} onClick={onClick}>{currText.text}</Link>
        );
    }else if(isHashLink){
        return(
            <HashLink className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} smooth to={checkTo} onClick={onClick}>{currText.text}</HashLink>
        );
    }else{
        return(
            <button className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} onClick={handleOnClick}>{currText.text}</button>
        );
    }
}