import React from 'react';

export default (props) => {
    console.warn(props, 'propes');

    const options = props.lista.map( (option, index)  => {
        return (
            <option
                key={index}
                value={option.id}
            >
                {option.name}
            </option>
        )
    })

    return (
        <select {...props}  >
            {options}
        </select>
    )
}
