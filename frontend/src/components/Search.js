import React, { useState } from 'react';
import '../styles/search.css';
import '../styles/edit_field.css';
import '../styles/button.css';
import axios from 'axios';
import { EditText } from './EditText';

export const Search = (props) => {

    const [query, setQuery] = useState('');

    function handleChange(text){
        setQuery(text);
    }

    function submitQuery(event){
        event.preventDefault();
        axios.get(`http://192.168.0.19:8080/api/note?page=0&search=${query}`)
        .then(response => {
            console.log(response.data);
            props.onSearch(response.data.content, response.data.totalPages);
        }).catch(error => console.log(error));
    }

    return(
        <div className='search-container'>
            <form onSubmit={submitQuery}>
                <EditText className='search-box' placeholder={props.placeholder} editStyle='edit-text-light' editSize='font-large' inputMode='text' type='text' onChange={handleChange} />
                <input className='btn btn-small btn-round-blue' type='submit' text='submit' />
            </form>
        </div>
    );

}