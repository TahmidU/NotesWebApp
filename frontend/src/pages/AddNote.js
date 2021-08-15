import React, { useState } from 'react';
import '../styles/add_page.css';
import '../styles/button.css';
import { EditText } from '../components/EditText';
import { Layout } from '../components/Layout';
import axios from 'axios';

export const AddNote = (props) => {

    const [note, setNote] = useState({
        title: '',
        description: ''
    });

    function getTitle(title){
        setNote({
            ...note,
            title: title
        });
    }

    function getDescText(description){
        setNote({
            ...note, 
            description: description
        });
    }

    function submitNote(event){
        event.preventDefault();
        axios.post('http://192.168.0.19:8080/api/note', {
            title: note.title,
            content: note.description
        }).then(response => {
            console.log(response.data);
            props.history.push('/noteboard');
        }).catch(error => console.log(error));
    }

    return (
        <Layout>
            <form onSubmit={submitNote}>
                <br/><br/>
                <label>Title:</label>
                <EditText className='title-box' limit={50} editStyle='edit-text-light' editSize='font-medium' onChange={getTitle}/>
                <br/>
                <label>Description:</label>
                <EditText className='desc-box' limit={280} editStyle='edit-text-light' editSize='font-medium' onChange={getDescText}/>
                <br/>
                <input className='btn btn-small btn-round-blue' type='submit' />

            </form>
        </Layout>
    );
}