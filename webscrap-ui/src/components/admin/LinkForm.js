import React from 'react'
import { Button, Form, Icon } from 'semantic-ui-react'

function LinkForm({ linkUrl, linkDescription, handleInputChange, handleAddLink }) {
  const createBtnDisabled = linkUrl.trim() === '' || linkDescription.trim() === ''
  return (
    <Form onSubmit={handleAddLink}>
      <Form.Group>
        <Form.Input
          name='linkUrl'
          placeholder='URL *'
          value={linkUrl}
          onChange={handleInputChange}
        />
        <Form.Input
          name='linkDescription'
          placeholder='Description *'
          value={linkDescription}
          onChange={handleInputChange}
        />
        <Button icon labelPosition='right' disabled={createBtnDisabled}>
          Create Link<Icon name='add' />
        </Button>
      </Form.Group>
    </Form>
  )
}

export default LinkForm