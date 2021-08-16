import React from 'react';
import { Link } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import '../styles/button.css';

const SIZES = ['btn-small', 'btn-medium', 'btn-large'];
const STYLES = ['', 'btn-round-blue', 'btn-round-red', 'btn-text-blue', 'btn-text-selected'];

export const Button = ({children, btnSize, btnStyle, className, isLink, isHashLink, onClick, to}) => {

    const checkBtnSize = SIZES.includes(btnSize) ? btnSize : SIZES[0];
    const checkBtnStyle = STYLES.includes(btnStyle) ? btnStyle : STYLES[0];
    const checkTo = to == null ? '/' : to;
    const checkClassName = className == null ? '' : className;

    function handleOnClick(){

        onClick();
    }
   
    if(isLink){
        return(
            <Link className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} to={checkTo} onClick={onClick}>{children}</Link>
        );
    }else if(isHashLink){
        return(
            <HashLink className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} smooth to={checkTo} onClick={onClick}>{children}</HashLink>
        );
    }else{
        return(
            <button className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} onClick={handleOnClick}>{children}</button>
        );
    }

}