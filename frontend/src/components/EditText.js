import React, { useState } from 'react';
import '../styles/edit_field.css';

const SIZES = ['font-medium', 'font-large'];
const STYLES = ['', 'edit-text-light', 'edit-text-error'];

export const EditText = (props) =>{

    const checkStyle = STYLES.includes(props.editStyle) ? props.editStyle : STYLES[0];
    const checkSize = SIZES.includes(props.editSize) ? props.editSize : SIZES[0];
    const checkClassName = props.className == null ? '' : props.className;
    const checkPlaceholder = props.placeholder == null ? '' : props.placeholder;
    const checkInputMode = props.inputMode == null ? 'text' : props.inputMode;
    const checkType = props.type == null ? 'text' : props.type;
    const checkName = props.name == null ? null : props.name;
    const checkMaxLength = props.maxLength == null ? -1 : props.maxLength;
    const checkTextPattern = props.textPattern == null ? "(.*?)" : props.textPattern;

    const [content, setContent] = useState({
        text: '',
        count: 0
    });

    if(content.text !== props.prefill && props.prefill !== null && typeof props.prefill !== 'undefined'){
        setContent({
            text: props.prefill,
            count: props.prefill.length
        })
    }

    function handleChange(event){
        setContent({
            text: event.target.value,
            count: event.target.value.length
        });
        props.onChange(event.target);
    }

    if(props.limit > -1){

        return(
            <>
                <textarea className={`edit-text ${checkSize} ${checkStyle} ${checkClassName}`} placeholder={checkPlaceholder} style={{margin:'1rem 0', resize:'none'}} inputMode={checkInputMode} type={checkType} maxLength={props.limit} onChange={handleChange} value={props.prefill}/>
                <p className='counter' style={{fontSize:'small'}}>{content.count}/{props.limit}</p>
            </>
        );
    }

    if(checkName !== null){
        return(
            <input id={checkName} name={checkName} className={`edit-text ${checkSize} ${checkStyle} ${checkClassName}`} maxLength={checkMaxLength} pattern={checkTextPattern} placeholder={checkPlaceholder} inputMode={checkInputMode} type={checkType} onChange={handleChange}/>
        );
    }

    if(props.error){
        return(
            <input className={`edit-text ${checkSize} ${STYLES[3]} ${checkClassName}`} maxLength={checkMaxLength} pattern={checkTextPattern} placeholder={checkPlaceholder} inputMode={checkInputMode} type={checkType} onChange={handleChange}/>
        );
    }

    return(
        <input className={`edit-text e ${checkSize} ${checkClassName}`} maxLength={checkMaxLength} pattern={checkTextPattern} placeholder={checkPlaceholder} inputMode={checkInputMode} type={checkType} onChange={handleChange}/>
    );




}