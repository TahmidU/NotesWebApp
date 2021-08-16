import React, { useEffect, useState } from 'react';
import '../styles/note_page.css';
import { Layout } from '../components/Layout';
import { EditText } from '../components/EditText';
import axios from 'axios';
import { Button } from '../components/Button';

export const EditNote = (props) => {

    const [note, setNote] = useState({
        title: '',
        description: ''
    });

    useEffect(getNote, [props.match.params.id]);

    function getNote(){
        axios.get(`http://localhost:8080/api/note/${props.match.params.id}`)
        .then(response => {
            console.log(response);
            setNote({
                title: response.data.title,
                description: response.data.content
            });
        }).then(error => console.log(error));
    }

    function getTitle(title){
        setNote({
            ...note,
            title: title
        });
    }

    function getDescription(description){
        setNote({
            ...note,
            description: description
        });
    }

    function submitNote(event){
        event.preventDefault();
        axios.put('http://localhost:8080/api/note', {
            noteId: props.match.params.id,
            title: note.title,
            content: note.description
        }).then(response => {
            console.log(response.data);
            props.history.push('/noteboard');
        }).catch(error => console.log(error));
    }

    return(
        <Layout>
            <form onSubmit={submitNote}>
                <br/><br/>
                <label>Title:</label>
                <EditText className='title-box' limit={50} editStyle='edit-text-light' editSize='font-medium' prefill={note.title} onChange={getTitle}/>
                <br/>
                <label>Description:</label>
                <EditText className='desc-box' limit={280} editStyle='edit-text-light' editSize='font-medium' prefill={note.description} onChange={getDescription}/>
                <br/>
                <div className='form-btn'>
                    <input className='btn btn-small btn-round-blue' type='submit' />
                    <Button btnSize='btn-small' btnStyle='btn-round-red' isLink={true} to='/noteboard'>Cancel</Button>
                </div>
                
            </form>
        </Layout>
    )

}