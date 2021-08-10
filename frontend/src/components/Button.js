import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/button.css';

const SIZES = ['btn-small', 'btn-medium', 'btn-large'];
const STYLES = ['', 'btn-round-blue', 'btn-text-blue'];

export const Button = ({children, btnSize, btnStyle, className, isLink, onClick, to}) => {

    const checkBtnSize = SIZES.includes(btnSize) ? btnSize : SIZES[0];
    const checkBtnStyle = STYLES.includes(btnStyle) ? btnStyle : STYLES[0];
    const checkClassName = className == null ? '' : className;
    
    const [currText, setCurrentText] = useState({count: 0, text : Array.isArray(children) ? children[0] : children});

    function handleOnClick(){

        setCurrentText((prevState) => {
            return{
                count: prevState.count + 1,
                text: children[(prevState.count + 1) % 2]
            }
        });

        onClick();
    }
   

    if(isLink){
        // You should not have children be an array if you are using a link since you will be switching pages. However, currText.text is used for safety.
        return(
            <Link className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} to={to}>{currText.text}</Link>
        );
    }else{
        return(
            <button className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkClassName}`} onClick={handleOnClick}>{currText.text}</button>
        );
    }

}