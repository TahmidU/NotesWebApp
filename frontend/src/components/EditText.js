import React, { useState } from 'react';
import '../styles/edit_field.css';

const SIZES = ['font-medium', 'font-large'];
const STYLES = ['', 'edit-text-light'];

export const EditText = (props) =>{

    const checkStyle = STYLES.includes(props.editStyle) ? props.editStyle : STYLES[0];
    const checkSize = SIZES.includes(props.editSize) ? props.editSize : SIZES[0];
    const checkClassName = props.className == null ? '' : props.className;
    const checkPlaceholder = props.placeholder == null ? '' : props.placeholder;

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
        props.onChange(event.target.value);
    }

    if(props.limit > -1){

        return(
            <>
                <textarea className={`edit-text ${checkSize} ${checkStyle} ${checkClassName}`} placeholder={checkPlaceholder} style={{margin:'1rem 0', resize:'none'}} inputMode='text' type='text' maxLength={props.limit} onChange={handleChange} value={props.prefill}/>
                <p className='counter' style={{fontSize:'small'}}>{content.count}/{props.limit}</p>
            </>
        );
    }

    return(
        <input className={`edit-text ${checkSize} ${checkStyle} ${checkClassName}`} placeholder={checkPlaceholder} inputMode='text' type='text' onChange={handleChange}/>
    );

}